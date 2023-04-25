package com.eventsbook.controller.request;

import com.eventsbook.domain.EventType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateSentMoneyRecordRequest {

    private String friendName;
    private String relationshipWithFriend;
    private String money;
    private EventType eventType;
    private String memo;
    private LocalDate transactionDate;
}
