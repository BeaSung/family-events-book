package com.eventsbook.controller;

import com.eventsbook.controller.request.AddReceivedMoneyRecordRequest;
import com.eventsbook.controller.request.AddSentMoneyRecordRequest;
import com.eventsbook.controller.response.GetRecordBooksResponse;
import com.eventsbook.service.RecordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/records")
@RestController
public class RecordBookController {

    private final RecordBookService service;

    @PostMapping("/sent-money")
    public void addSentMoney(@RequestBody AddSentMoneyRecordRequest request) {
        service.addSentMoneyRecord(0L, request);
    }

    @PostMapping("/received-money")
    public void addReceivedMoney(@RequestBody AddReceivedMoneyRecordRequest request) {
        service.addReceivedMoneyRecord(0L, request);
    }

    @GetMapping
    public GetRecordBooksResponse getRecordBooks(@RequestParam(required = false) Long lastId) {
        return new GetRecordBooksResponse();
    }
}
