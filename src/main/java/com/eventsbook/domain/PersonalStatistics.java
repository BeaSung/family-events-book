package com.eventsbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "personal_statistics")
public class PersonalStatistics {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "sent_money_total_count", nullable = false)
    private Long sentMoneyTotalCount;

    @Column(name = "sent_money_sum", nullable = false)
    private BigDecimal sentMoneySum;

    @Column(name = "received_money_total_count", nullable = false)
    private Long receivedMoneyTotalCount;

    @Column(name = "received_money_sum", nullable = false)
    private BigDecimal receivedMoneySum;

    public PersonalStatistics(Long userId,
                              Long sentMoneyTotalCount,
                              BigDecimal sentMoneySum,
                              Long receivedMoneyTotalCount,
                              BigDecimal receivedMoneySum) {
        this.userId = userId;
        this.sentMoneyTotalCount = sentMoneyTotalCount;
        this.sentMoneySum = sentMoneySum;
        this.receivedMoneyTotalCount = receivedMoneyTotalCount;
        this.receivedMoneySum = receivedMoneySum;
    }

    public static PersonalStatistics initOf(Long userId) {
        return new PersonalStatistics(userId, 0L, BigDecimal.ZERO, 0L, BigDecimal.ZERO);
    }

    public void addMoney(TransactionType transactionType, BigDecimal money) {
        if (transactionType == TransactionType.SENT) {
            this.sentMoneyTotalCount++;
            this.sentMoneySum = sentMoneySum.add(money);
        } else {
            this.receivedMoneyTotalCount++;
            this.receivedMoneySum = receivedMoneySum.add(money);
        }
    }

    public void subMoney(TransactionType transactionType, BigDecimal money) {
        if (transactionType == TransactionType.SENT) {
            this.sentMoneyTotalCount--;
            this.sentMoneySum = sentMoneySum.subtract(money);
        } else {
            this.receivedMoneyTotalCount--;
            this.receivedMoneySum = receivedMoneySum.subtract(money);
        }
    }

    public void changeMoney(TransactionType transactionType, BigDecimal beforeMoney, BigDecimal afterMoney) {

        if (transactionType == TransactionType.SENT) {
            if (beforeMoney.compareTo(afterMoney) < 0) {
                BigDecimal difference = afterMoney.subtract(beforeMoney);
                this.sentMoneySum = sentMoneySum.add(difference);
            } else {
                BigDecimal difference = beforeMoney.subtract(afterMoney);
                this.sentMoneySum = sentMoneySum.subtract(difference);
            }
        } else {
            if (beforeMoney.compareTo(afterMoney) < 0) {
                BigDecimal difference = afterMoney.subtract(beforeMoney);
                this.receivedMoneySum = receivedMoneySum.add(difference);
            } else {
                BigDecimal difference = beforeMoney.subtract(afterMoney);
                this.receivedMoneySum = receivedMoneySum.subtract(difference);
            }
        }
    }
}
