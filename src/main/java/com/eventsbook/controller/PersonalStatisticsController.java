package com.eventsbook.controller;

import com.eventsbook.controller.response.GetPersonalStatisticsResponse;
import com.eventsbook.domain.PersonalStatistics;
import com.eventsbook.service.PersonalStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PersonalStatisticsController {

    private final PersonalStatisticsService service;

    @GetMapping("/records/personal-statistics")
    public GetPersonalStatisticsResponse getPersonalStatistics() {
        var personalStatistics = service.getPersonalStatistics(0L);
        if (personalStatistics.isEmpty()) {
            return null;
        } else {
            PersonalStatistics statistics = personalStatistics.get();
            return new GetPersonalStatisticsResponse(statistics.getSentMoneyTotalCount(),
                    statistics.getSentMoneySum().toString(),
                    statistics.getReceivedMoneyTotalCount(),
                    statistics.getReceivedMoneySum().toString());
        }
    }
}
