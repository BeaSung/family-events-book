package com.eventsbook.remote.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoTokenResponse(@JsonProperty("access_token") String accessToken,
                                 @JsonProperty("expires_in") long expirationSeconds) {
}
