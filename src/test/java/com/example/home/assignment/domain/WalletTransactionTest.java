package com.example.home.assignment.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletTransactionTest {

    @Test
    void properlyCalculatesBaseCurrencyAmount() {
        var currencyAmount = 10.0;
        var baseCurrencyRate = 0.8;
        var walletTransaction = new WalletTransaction("BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                "2023-06-16T11:07:36.639Z",
                currencyAmount,
                "USD",
                "EUR"
        ).calculateBaseCurrencyAmount(baseCurrencyRate);

        var expectedBaseCurrencyAmount = 8;

        assertEquals(expectedBaseCurrencyAmount, walletTransaction.getBaseCurrencyAmount());
    }
}
