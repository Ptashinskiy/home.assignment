package com.example.home.assignment.domain;

/**
 * Entity class representing a wallet transaction.
 */
public class WalletTransaction {

    private String type;
    private String playerId;
    private String gameId;
    private String transactionId;
    private String timestamp;
    private Double amount;
    private String currency;
    private String baseCurrency;
    private Double baseCurrencyAmount;

    public WalletTransaction(String type, String playerId, String gameId, String transactionId, String timestamp,
                             Double amount, String currency, String baseCurrency) {
        this.type = type;
        this.playerId = playerId;
        this.gameId = gameId;
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.baseCurrency = baseCurrency;
    }


    /**
     * Calculates the base currency amount using the provided base currency rate.
     *
     * @param baseCurrencyRate The rate used to calculate the base currency amount.
     * @return The updated WalletTransactionEntity object.
     */
    public WalletTransaction calculateBaseCurrencyAmount(Double baseCurrencyRate) {
        this.baseCurrencyAmount = amount * baseCurrencyRate;
        return this;
    }
}
