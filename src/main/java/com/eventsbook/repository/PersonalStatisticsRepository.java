package com.eventsbook.repository;

import com.eventsbook.domain.PersonalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalStatisticsRepository extends JpaRepository<PersonalStatistics, Long> {

    Optional<PersonalStatistics> findByUserId(Long userId);
}
