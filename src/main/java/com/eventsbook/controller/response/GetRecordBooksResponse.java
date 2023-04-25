package com.eventsbook.controller.response;

import com.eventsbook.domain.EventType;
import com.eventsbook.domain.RecordBook;
import com.eventsbook.domain.TransactionType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetRecordBooksResponse {

    private final List<GetRecordBookDTO> recordBooks;

    @Data
    public static class GetRecordBookDTO {
        private Long id;
        private String friendName;
        private String relationshipWithFriend;
        private String money;
        private LocalDate transactionDate;
        private EventType eventType;
        private TransactionType transactionType;

        public GetRecordBookDTO(RecordBook recordBook) {
            this.id = recordBook.getId();
            this.friendName = recordBook.getFriend().getName();
            this.relationshipWithFriend = recordBook.getFriend()
                    .getRelationshipWithFriend()
                    .orElse("");
            this.money = recordBook.getMoney().toString();
            this.transactionDate = recordBook.getTransactionDate();
            this.eventType = recordBook.getEventType();
            this.transactionType = recordBook.getTransactionType();
        }
    }
}
