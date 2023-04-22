package com.eventsbook.repository;

import com.eventsbook.domain.RecordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordBookRepository extends JpaRepository<RecordBook, Long> {
}
