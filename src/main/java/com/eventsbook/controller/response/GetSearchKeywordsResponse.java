package com.eventsbook.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class GetSearchKeywordsResponse {
    private final List<String> keywords;
}
