package com.eventsbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "statistics")
public class Statistics {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year", nullable = false)
    private Year year;

    @Enumerated(STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Enumerated(STRING)
    @Column(name = "age_range", nullable = false)
    private AgeRange ageRange;

    @Column(name = "total_count", nullable = false)
    private Long totalCount;

    @Column(name = "total_money", nullable = false)
    private BigDecimal totalMoney;

    public BigDecimal averageMoney() {
        return totalMoney.divide(BigDecimal.valueOf(totalCount), RoundingMode.HALF_UP);
    }
}
