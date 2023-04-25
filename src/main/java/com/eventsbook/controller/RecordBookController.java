package com.eventsbook.controller;

import com.eventsbook.controller.request.AddReceivedMoneyRecordRequest;
import com.eventsbook.controller.request.AddSentMoneyRecordRequest;
import com.eventsbook.controller.response.GetRecordBooksResponse;
import com.eventsbook.controller.response.GetRecordBooksResponse.GetRecordBookDTO;
import com.eventsbook.domain.EventType;
import com.eventsbook.domain.TransactionType;
import com.eventsbook.service.GetRecordBookService;
import com.eventsbook.service.RecordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;

@RequiredArgsConstructor
@RequestMapping("/records")
@RestController
public class RecordBookController {

    private final RecordBookService service;
    private final GetRecordBookService getRecordBookService;

    @PostMapping("/sent-money")
    public void addSentMoney(@RequestBody AddSentMoneyRecordRequest request) {
        service.addSentMoneyRecord(0L, request);
    }

    @PostMapping("/received-money")
    public void addReceivedMoney(@RequestBody AddReceivedMoneyRecordRequest request) {
        service.addReceivedMoneyRecord(0L, request);
    }

    @GetMapping
    public GetRecordBooksResponse getRecordBooks(@RequestParam(required = false) LocalDate lastDate,
                                                 @RequestParam(required = false) Long lastRecordId,
                                                 @RequestParam(required = false) TransactionType transactionType,
                                                 @RequestParam(required = false) EventType eventType,
                                                 @RequestParam(required = false) YearMonth yearMonth) {
        var recordBooks = getRecordBookService.getRecordBooks(0L, lastDate, lastRecordId, transactionType, eventType, yearMonth);

        return new GetRecordBooksResponse(recordBooks.stream()
                .map(GetRecordBookDTO::new)
                .toList());
    }
}
