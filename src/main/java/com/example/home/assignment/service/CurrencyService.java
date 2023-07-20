package com.example.home.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Component class that interacts with a currency service to retrieve currency rates.
 */
@Component
public class CurrencyService {

    private final static Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    private final static String GET_CURRENCY_RATE_BASE_URL = "/currency/rate/{scr}/{dst}?at={timestamp}";

    private final RestTemplate currencyServiceRestTemplate;

    @Autowired
    public CurrencyService(RestTemplate currencyServiceRestTemplate) {
        this.currencyServiceRestTemplate = currencyServiceRestTemplate;
    }

    /**
     * Retrieves the currency rate for the specified currency and base currency at the given timestamp.
     *
     * @param currency     The currency for which the rate is requested.
     * @param baseCurrency The base currency in which the rate is requested.
     * @param timestamp    The timestamp at which the rate is requested.
     * @return The currency rate.
     * @throws CurrencyException If unable to receive the currency rate.
     */
    @Cacheable(cacheNames = "currency-rates")
    public CurrencyRate getCurrencyRate(String currency, String baseCurrency, String timestamp) {
        logger.info("Retrieving rate for currency {} and base currency {} at {}.", currency, baseCurrency, timestamp);
        var uriVariables = Map.of("scr", currency, "dst", baseCurrency, "timestamp", timestamp);
        try {
            return currencyServiceRestTemplate.exchange(
                    GET_CURRENCY_RATE_BASE_URL,
                    HttpMethod.GET,
                    null,
                    CurrencyRate.class,
                    uriVariables
            ).getBody();
        } catch (RestClientException e) {
            logger.error("Unable to receive rate for {} to {} currencies at {}.", currency, baseCurrency, timestamp);
            throw CurrencyException.unableToReceiveRate(currency, baseCurrency, timestamp);
        }
    }
}
