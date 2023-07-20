package com.example.home.assignment.messaging;

import com.example.home.assignment.domain.WalletTransaction;
import com.example.home.assignment.messaging.WalletTransactionMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTransactionMessageTest {


    @Test
    void properlyConvertsToEntity() {
        var type = "BET";
        var playerId = "123424120";
        var gameId = "european_roulette";
        var transactionId = "7d7a0b82-0c47-11ee-be56-0242ac120002";
        var timestamp = "2023-06-16T06:00:00.000Z";
        var amount = 10.0;
        var currency = "USD";
        var baseCurrency = "EUR";
        var message = new WalletTransactionMessage(
                type, playerId, gameId, transactionId, timestamp, amount, currency, baseCurrency
        );
        var expectedEntity = new WalletTransaction(
                type, playerId, gameId, transactionId, timestamp, amount, currency, baseCurrency
        );

        var actualEntity = message.toEntity();

        assertEquals(expectedEntity, actualEntity);
    }

}
