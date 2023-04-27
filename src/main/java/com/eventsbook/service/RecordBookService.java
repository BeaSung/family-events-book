package com.eventsbook.service;

import com.eventsbook.controller.request.AddReceivedMoneyRecordRequest;
import com.eventsbook.controller.request.AddSentMoneyRecordRequest;
import com.eventsbook.controller.request.UpdateReceivedMoneyRecordRequest;
import com.eventsbook.controller.request.UpdateSentMoneyRecordRequest;
import com.eventsbook.domain.Friend;
import com.eventsbook.domain.RecordBook;
import com.eventsbook.repository.RecordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class RecordBookService {

    private final RecordBookRepository repository;

    @Transactional
    public void addSentMoneyRecord(Long userId, AddSentMoneyRecordRequest request) {
        var recordBook = RecordBook.ofSent(userId,
                new Friend(request.getFriendName(), request.getRelationshipWithFriend()),
                new BigDecimal(request.getMoney()),
                request.getEventType(),
                request.getMemo(),
                request.getTransactionDate());

        repository.save(recordBook);
    }

    @Transactional
    public void addReceivedMoneyRecord(Long userId, AddReceivedMoneyRecordRequest request) {
        var recordBook = RecordBook.ofReceived(userId,
                request.getFriendName(),
                new BigDecimal(request.getMoney()),
                request.getEventType(),
                request.getMemo(),
                request.getTransactionDate());

        repository.save(recordBook);
    }

    @Transactional
    public void updateSentMoneyRecord(Long recordId, UpdateSentMoneyRecordRequest request) {
        RecordBook recordBook = repository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정보입니다."));

        recordBook.changeSentMoneyRecord(request.getFriendName(),
                request.getRelationshipWithFriend(),
                new BigDecimal(request.getMoney()),
                request.getEventType(),
                request.getMemo(),
                request.getTransactionDate());
    }

    @Transactional
    public void updateReceivedMoneyRecord(Long recordId, UpdateReceivedMoneyRecordRequest request) {
        RecordBook recordBook = repository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정보입니다."));

        recordBook.changeReceivedMoneyRecord(request.getFriendName(),
                new BigDecimal(request.getMoney()),
                request.getEventType(),
                request.getMemo(),
                request.getTransactionDate());
    }

    @Transactional
    public void deleteRecord(Long recordId) {
        repository.deleteById(recordId);
    }
}
