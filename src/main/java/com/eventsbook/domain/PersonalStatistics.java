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
}
