package com.eventsbook.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "record_book")
@EntityListeners(AuditingEntityListener.class)
public class RecordBook {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Embedded
    private Friend friend;

    @Column(name = "money", nullable = false)
    private BigDecimal money;

    @Enumerated(STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Enumerated(STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "memo", nullable = false)
    private String memo;

    @Column(name = "transaction_date", nullable = false, columnDefinition = "DATE")
    private LocalDate transactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private RecordBook(Long userId,
                       Friend friend,
                       BigDecimal money,
                       EventType eventType,
                       TransactionType transactionType,
                       String memo,
                       LocalDate transactionDate) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("금액은 마이너스가 될 수 없습니다.");
        }
        this.userId = userId;
        this.friend = friend;
        this.money = money;
        this.eventType = eventType;
        this.transactionType = transactionType;
        this.memo = memo;
        this.transactionDate = transactionDate;
    }

    public static RecordBook ofSent(Long userId,
                                    Friend friend,
                                    BigDecimal money,
                                    EventType eventType,
                                    String memo,
                                    LocalDate transactionDate) {
        return new RecordBook(userId,
                friend,
                money,
                eventType,
                TransactionType.SENT,
                memo,
                transactionDate);
    }

    public static RecordBook ofReceived(Long userId,
                                        String friendName,
                                        BigDecimal money,
                                        EventType eventType,
                                        String memo,
                                        LocalDate transactionDate) {
        return new RecordBook(userId,
                new Friend(friendName, null),
                money,
                eventType,
                TransactionType.RECEIVED,
                memo,
                transactionDate);
    }

    public void changeSentMoneyRecord(String friendName,
                                      String relationshipWithFriend,
                                      BigDecimal money,
                                      EventType eventType,
                                      String memo,
                                      LocalDate transactionDate) {
        this.friend = new Friend(friendName, relationshipWithFriend);
        this.money = money;
        this.eventType = eventType;
        this.memo = memo;
        this.transactionDate = transactionDate;
    }

    public void changeReceivedMoneyRecord(String friendName,
                                      BigDecimal money,
                                      EventType eventType,
                                      String memo,
                                      LocalDate transactionDate) {
        this.friend = new Friend(friendName, null);
        this.money = money;
        this.eventType = eventType;
        this.memo = memo;
        this.transactionDate = transactionDate;
    }
}
