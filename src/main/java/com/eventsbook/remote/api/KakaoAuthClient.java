package com.eventsbook.remote.api;

import com.eventsbook.remote.api.response.KakaoTokenResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

public interface KakaoAuthClient {

    @PostExchange("/oauth/token")
    KakaoTokenResponse getToken(@RequestParam("grant_type") String grantType,
                                @RequestParam("client_id") String clientId,
                                @RequestParam("redirect_uri") String redirectUri,
                                @RequestParam("code") String code);
}
