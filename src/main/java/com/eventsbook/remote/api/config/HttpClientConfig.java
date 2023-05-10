package com.eventsbook.remote.api.config;

import com.eventsbook.remote.api.KakaoAuthClient;
import com.eventsbook.service.GetRecordBookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    public WebClient kakaoAuthWebClient() {
        return WebClient.builder()
                        .baseUrl("https://kauth.kakao.com")
                        .build();
    }

    @Bean
    public HttpServiceProxyFactory kakaoAuthHttpServiceProxyFactory(WebClient kakaoAuthWebClient) {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(kakaoAuthWebClient))
                                      .build();
    }

    @Bean
    public KakaoAuthClient kakaoAuthClient(HttpServiceProxyFactory kakaoAuthHttpServiceProxyFactory) {
        return kakaoAuthHttpServiceProxyFactory.createClient(KakaoAuthClient.class);
    }
}
