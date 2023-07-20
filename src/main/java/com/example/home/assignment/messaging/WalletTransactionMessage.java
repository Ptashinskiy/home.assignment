package com.example.home.assignment.messaging;

import com.example.home.assignment.domain.WalletTransaction;

/**
 * Message class representing a wallet transaction.
 */
public record WalletTransactionMessage(String type,
                                       String playerId,
                                       String gameId,
                                       String transactionId,
                                       /* Using String type for timestamp parameter because there is no specific
                                        functionality that requires Timestamp
                                       */
                                       String timestamp,
                                       Double amount,
                                       String currency,
                                       String baseCurrency) {

    /**
     * Converts the {@link WalletTransactionMessage} to a {@link WalletTransaction}.
     *
     * @return The converted WalletTransaction.
     */
    public WalletTransaction toEntity() {
        return new WalletTransaction(
                type,
                playerId,
                gameId,
                transactionId,
                timestamp,
                amount,
                currency,
                baseCurrency
        );
    }
}
