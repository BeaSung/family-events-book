package com.eventsbook.service;

import com.eventsbook.domain.EventType;
import com.eventsbook.domain.RecordBook;
import com.eventsbook.domain.TransactionType;
import com.eventsbook.repository.RecordBookRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRecordBookService {

    private final RecordBookRepository repository;

    @Transactional(readOnly = true)
    public List<RecordBook> getRecordBooks(Long userId,
                                           @Nullable LocalDate lastDate,
                                           @Nullable Long lastRecordId,
                                           @Nullable TransactionType transactionType,
                                           @Nullable EventType eventType,
                                           @Nullable YearMonth yearMonth) {
        if (transactionType != null) {
            return getRecordsByTransactionType(userId, transactionType, lastDate, lastRecordId);
        } else if (eventType != null) {
            return getRecordsByEventType(userId, eventType, lastDate, lastRecordId);
        } else if (yearMonth != null) {
            return getRecordsByMonth(userId, yearMonth);
        }
        return getRecordBooks(userId, lastDate, lastRecordId);
    }

    private List<RecordBook> getRecordBooks(Long userId,
                                            @Nullable LocalDate lastDate,
                                            @Nullable Long lastRecordId) {
        if (lastDate == null || lastRecordId == null) {
            /**
             * 첫번째 호출 => 가장 최근에 등록된 데이터 N개를 반환
             * 현재날짜 기준(2023-4-24) 부터 10개를 반환 -> 마지막 RecordBook 의 데이터 lastDate=2023-4-10, lastRecordId=10
             *  - 2023-4-10 -> 이 날짜에 등록된 데이터가 10개지만, but 응답에 포함되는 데이터는 8개.
             *  - example
             *      - id = 10, transaction_date = 2023-4-10 <- lastRecord
             *      - id = 9, transaction_date = 2023-4-10 <- next page
             *      - id = 8, transaction_date = 2023-4-10 <- next page
             */
            return repository.findTop10ByUserIdOrderByTransactionDateDesc(userId);
        }
        /**
         * N번째 호출 => lastId - 1 부터 N개를 반환
         * lastDate 랑 같거나 더 과거 날짜의 목록을 조회 & lastRecordId 보다 더 적은 Id(PK) 값을 가지는 데이터를 조회
         */
        Pageable pageable = Pageable.ofSize(10);
        return repository.findRecordBooks(userId, lastDate, lastRecordId, pageable);
    }

    private List<RecordBook> getRecordsByTransactionType(Long userId,
                                                         TransactionType transactionType,
                                                         @Nullable LocalDate lastDate,
                                                         @Nullable Long lastRecordId) {
        if (lastDate == null || lastRecordId == null) {
            return repository.findRecordsByTransactionType(userId, transactionType);
        }
        Pageable pageable = Pageable.ofSize(10);
        return repository.findRecordsByTransactionType(userId, transactionType, lastDate, lastRecordId, pageable);
    }

    private List<RecordBook> getRecordsByEventType(Long userId,
                                                   EventType eventType,
                                                   @Nullable LocalDate lastDate,
                                                   @Nullable Long lastRecordId) {
        if (lastDate == null || lastRecordId == null) {
            return repository.findRecordsByEventType(userId, eventType);
        }
        Pageable pageable = Pageable.ofSize(10);
        return repository.findRecordsByEventType(userId, eventType, lastDate, lastRecordId, pageable);
    }

    private List<RecordBook> getRecordsByMonth(Long userId, YearMonth yearMonth) {
        return repository.findRecordsByMonth(userId, yearMonth.atDay(1), yearMonth.atEndOfMonth());
    }
}
