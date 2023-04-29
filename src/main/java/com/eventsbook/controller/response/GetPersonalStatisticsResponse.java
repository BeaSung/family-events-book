package com.eventsbook.controller.response;

import lombok.Data;

@Data
public class GetPersonalStatisticsResponse {

    private final Long sentMoneyTotalCount;
    private final String sentMoneySum;
    private final Long receivedMoneyTotalCount;
    private final String receivedMoneySum;
}
