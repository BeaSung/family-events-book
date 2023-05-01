package com.eventsbook.service;

import com.eventsbook.domain.PersonalStatistics;
import com.eventsbook.domain.TransactionType;
import com.eventsbook.repository.PersonalStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonalStatisticsService {

    private final PersonalStatisticsRepository repository;

    @Transactional(readOnly = true)
    public Optional<PersonalStatistics> getPersonalStatistics(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional
    public void addStatistics(Long userId, BigDecimal money, TransactionType transactionType) {
        PersonalStatistics statistics = repository.findByUserId(userId)
                .orElseGet(() -> PersonalStatistics.initOf(userId));

        statistics.addMoney(transactionType, money);
        repository.save(statistics);
    }

    @Transactional
    public void changeStatistics(Long userId, BigDecimal beforeMoney, BigDecimal afterMoney, TransactionType transactionType) {
        PersonalStatistics statistics = repository.findByUserId(userId)
                .orElseThrow(IllegalStateException::new);
        statistics.changeMoney(transactionType, beforeMoney, afterMoney);
    }

    @Transactional
    public void subtractStatistics(Long userId, BigDecimal money, TransactionType transactionType) {
        PersonalStatistics statistics = repository.findByUserId(userId)
                .orElseThrow(IllegalStateException::new);

        statistics.subMoney(transactionType, money);
    }
}
