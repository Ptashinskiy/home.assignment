package com.example.home.assignment.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WalletTransactionServiceTest {

    @InjectMocks
    private WalletTransactionService walletTransactionService;

    @Mock
    private WalletTransactionRepository walletTransactionRepository;

    @Test
    void savesEnrichedWalletTransaction() {
        var currencyRate = 0.8;
        var walletTransaction = new WalletTransaction(
                "BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                "2023-06-16T06:00:00.000Z",
                10.0,
                "USD",
                "EUR"
        );

        walletTransactionService.enrichAndSave(walletTransaction, currencyRate);

        verify(walletTransactionRepository).save(eq(walletTransaction));
    }
}
