package com.eventsbook.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventType {
    WEDDING("결혼"),
    FUNERAL("장례식"),
    CHILDBIRTH("출산"),
    BIRTHDAY("생일"),
    BIRTHDAY_61YEARS("환갑"),
    BIRTHDAY_1YEAR("돌"),
    BIRTHDAY_100DAYS("백일"),
    OPENING_CEREMONY("개업/창업"),
    MOVE("이사/이전"),
    ETC("기타");

    private final String name;
}
