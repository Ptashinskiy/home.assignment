package com.example.home.assignment.service;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.okForJson;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest
class CurrencyServiceTest {

    @Test
    void returnsCurrencyRateWhenResponseIsOk(WireMockRuntimeInfo wireMockRuntimeInfo) {
        var scr = "USD";
        var dst = "EUR";
        var timestamp = "2023-06-16T06:00:00.000Z";
        var restTemplate = new RestTemplateBuilder().rootUri(wireMockRuntimeInfo.getHttpBaseUrl()).build();
        var currencyService = new CurrencyService(restTemplate);
        var expectedCurrencyRate = new CurrencyRate("USD", "EUR", "2023-06-16T06:00:00.000Z", 10.2);

        stubFor(get(urlPathEqualTo(String.format("/currency/rate/%s/%s", scr, dst)))
                .withQueryParam("at", equalTo(timestamp))
                .willReturn(okForJson(expectedCurrencyRate))
        );

        var actualCurrencyRate = currencyService.getCurrencyRate(scr, dst, timestamp);

        assertEquals(expectedCurrencyRate, actualCurrencyRate);
    }

    @Test
    void throwsExceptionIfNotOkResponse(WireMockRuntimeInfo wireMockRuntimeInfo) {
        var scr = "USD";
        var dst = "EUR";
        var timestamp = "2023-06-16T06:00:00.000Z";
        var restTemplate = new RestTemplateBuilder().rootUri(wireMockRuntimeInfo.getHttpBaseUrl()).build();
        var currencyService = new CurrencyService(restTemplate);

        stubFor(get(urlPathEqualTo(String.format("/currency/rate/%s/%s", scr, dst)))
                .withQueryParam("at", equalTo(timestamp))
                .willReturn(badRequest())
        );

        assertThrows(CurrencyException.class, () -> currencyService.getCurrencyRate(scr, dst, timestamp));
    }
}
