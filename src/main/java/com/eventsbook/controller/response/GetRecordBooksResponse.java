package com.eventsbook.controller.response;

import com.eventsbook.domain.EventType;
import com.eventsbook.domain.TransactionType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetRecordBooksResponse {

    private final List<GetRecordBookDTO> recordBooks;

    public static class GetRecordBookDTO {
        private Long id;
        private String friendName;
        private String relationshipWithFriend;
        private String money;
        private LocalDate transactionDate;
        private EventType eventType;
        private TransactionType transactionType;
    }
}
