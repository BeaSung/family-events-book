package com.eventsbook.service;

import com.eventsbook.domain.PersonalStatistics;
import com.eventsbook.repository.PersonalStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonalStatisticsService {

    private final PersonalStatisticsRepository repository;

    @Transactional(readOnly = true)
    public Optional<PersonalStatistics> getPersonalStatistics(Long userId) {
        return repository.findByUserId(userId);
    }
}
