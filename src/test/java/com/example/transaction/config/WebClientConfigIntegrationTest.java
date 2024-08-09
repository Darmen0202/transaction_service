package com.example.transaction.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WebClientConfigIntegrationTest {

    @Value("${exchange.rate.api.base-url}")
    private String baseUrl;

    @Value("${exchange.rate.api.key}")
    private String apiKey;

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebClientConfig webClientConfig;

    @Test
    public void testWebClientConfiguration() {
        assertEquals("https://api.twelvedata.com", baseUrl);

        String date = "2024-08-10";

        String uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/currency_conversion")
                .queryParam("symbol", "USD/RUB,USD/KZT")
                .queryParam("date", date)
                .queryParam("apikey", apiKey)
                .build()
                .toUriString();

        System.out.println("Request URI: " + uri);

        WebClient.RequestHeadersUriSpec<?> request = webClient.get();
        WebClient.RequestHeadersSpec<?> headersSpec = request.uri(uriBuilder ->
                uriBuilder.path("/currency_conversion")
                        .queryParam("symbol", "USD/RUB,USD/KZT")
                        .queryParam("date", date)
                        .queryParam("apikey", apiKey)
                        .build()
        );

        headersSpec.retrieve().toBodilessEntity().block();

        assertEquals(uri, uri);
    }
}


