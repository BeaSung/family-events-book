package com.eventsbook.repository;

import com.eventsbook.domain.EventType;
import com.eventsbook.domain.RecordBook;
import com.eventsbook.domain.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecordBookRepository extends JpaRepository<RecordBook, Long> {

    List<RecordBook> findTop10ByUserIdOrderByTransactionDateDesc(Long userId);

    // JPQL 에서는 Limit query 가 없어서 Pageable 로 대체한다
    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.transactionDate <= :date
            AND r.id < :lastRecordId
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordBooks(@Param("userId") Long userId,
                                     @Param("date") LocalDate date,
                                     @Param("lastRecordId") Long lastRecordId,
                                     Pageable pageable);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.friend.name = :friendName
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByFriendName(@Param("userId") Long userId,
                                     @Param("friendName") String friendName);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.transactionType = :transactionType
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByTransactionType(@Param("userId") Long userId,
                                                  @Param("transactionType") TransactionType transactionType);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.transactionType = :transactionType
            AND r.transactionDate <= :date
            AND r.id < :lastRecordId
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByTransactionType(@Param("userId") Long userId,
                                                  @Param("transactionType") TransactionType transactionType,
                                                  @Param("date") LocalDate date,
                                                  @Param("lastRecordId") Long lastRecordId,
                                                  Pageable pageable);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.eventType = :eventType
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByEventType(@Param("userId") Long userId,
                                            @Param("eventType") EventType eventType);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.eventType = :eventType
            AND r.transactionDate <= :date
            AND r.id < :lastRecordId
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByEventType(@Param("userId") Long userId,
                                            @Param("eventType") EventType eventType,
                                            @Param("date") LocalDate date,
                                            @Param("lastRecordId") Long lastRecordId,
                                            Pageable pageable);

    @Query("""
            SELECT r
            FROM RecordBook r
            WHERE r.userId = :userId
            AND r.transactionDate >= :startDate
            AND r.transactionDate <= :endDate
            ORDER BY r.transactionDate DESC
            """)
    List<RecordBook> findRecordsByMonth(@Param("userId") Long userId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);
}
