package com.example.home.assignment.service;

import com.example.home.assignment.domain.WalletTransaction;
import com.example.home.assignment.domain.WalletTransactionService;
import com.example.home.assignment.messaging.WalletTransactionMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessingServiceTest {

    @InjectMocks
    private ProcessingService processingService;

    @Mock
    private WalletTransactionService walletTransactionService;
    @Mock
    private CurrencyService currencyService;

    @Test
    void processWorksCorrectly() {
        var currency = "USD";
        var baseCurrency = "EUR";
        var timestamp = "2023-06-16T11:07:36.639Z";
        var currencyRateValue = 0.8;
        var message = new WalletTransactionMessage(
                "BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                timestamp,
                10.0,
                currency,
                baseCurrency
        );
        var currencyRate = new CurrencyRate(currency, baseCurrency, timestamp, currencyRateValue);
        var expectedWalletTransaction = new WalletTransaction("BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                timestamp,
                10.0,
                currency,
                baseCurrency
        );

        when(currencyService.getCurrencyRate(currency, baseCurrency, timestamp)).thenReturn(currencyRate);

        processingService.process(message);

        verify(currencyService).getCurrencyRate(currency, baseCurrency, timestamp);
        verify(walletTransactionService).enrichAndSave(expectedWalletTransaction, currencyRate.value());
    }

    @Test
    void noFurtherInteractionOnException() {
        var currency = "USD";
        var baseCurrency = "EUR";
        var timestamp = "2023-06-16T11:07:36.639Z";
        var message = new WalletTransactionMessage(
                "BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                timestamp,
                10.0,
                currency,
                baseCurrency
        );

        when(currencyService.getCurrencyRate(currency, baseCurrency, timestamp))
                .thenThrow(CurrencyException.class);

        processingService.process(message);

        verifyNoMoreInteractions(currencyService);
        verifyNoInteractions(walletTransactionService);
    }
}
