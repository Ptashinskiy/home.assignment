package com.example.home.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CurrencyServiceConfig {

    /**
     * The host value stored in configuration file and using to build RestTemplate instance.
     */
    @Value("${currency-service.host}")
    private String host;

    /**
     * Creates a RestTemplate bean for interacting in the currency service.
     *
     * @param restTemplateBuilder The RestTemplateBuilder used to build the RestTemplate.
     * @return The RestTemplate bean.
     */
    @Bean
    public RestTemplate currencyServiceRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(host).build();
    }
}
