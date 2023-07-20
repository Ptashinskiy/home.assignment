package com.example.home.assignment.service;

import com.example.home.assignment.service.CurrencyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyExceptionTest {

    @Test
    void exceptionMessageIsCorrect() {
        var currency = "USD";
        var baseCurrency = "EUR";
        var timestamp = "2023-06-16T06:00:00.000Z";
        var expectedMessage = "Unable to receive rate for USD to EUR currencies at 2023-06-16T06:00:00.000Z.";

        var currencyException = CurrencyException.unableToReceiveRate(currency, baseCurrency, timestamp);

        assertEquals(expectedMessage, currencyException.getMessage());
    }

}
