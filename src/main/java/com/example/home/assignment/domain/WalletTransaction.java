package com.example.home.assignment.domain;

import java.util.Objects;

/**
 * Entity class representing a wallet transaction.
 */
public class WalletTransaction {

    private final String type;
    private final String playerId;
    private final String gameId;
    private final String transactionId;
    private final String timestamp;
    private final Double amount;
    private final String currency;
    private final String baseCurrency;
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

    public Double getBaseCurrencyAmount() {
        return baseCurrencyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletTransaction that = (WalletTransaction) o;
        return Objects.equals(type, that.type) && Objects.equals(playerId, that.playerId) &&
                Objects.equals(gameId, that.gameId) && Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(timestamp, that.timestamp) && Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) && Objects.equals(baseCurrency, that.baseCurrency) &&
                Objects.equals(baseCurrencyAmount, that.baseCurrencyAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, playerId, gameId, transactionId, timestamp, amount, currency, baseCurrency,
                baseCurrencyAmount);
    }
}
