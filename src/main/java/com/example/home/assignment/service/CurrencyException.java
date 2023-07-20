package com.example.home.assignment.service;

public class CurrencyException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Unable to receive rate for %s to %s currencies at %s.";

    private CurrencyException(String message) {
        super(message);
    }

    public static CurrencyException unableToReceiveRate(String currency, String baseCurrency, String timeStamp) {
        String message = String.format(MESSAGE_TEMPLATE, currency, baseCurrency, timeStamp);
        return new CurrencyException(message);
    }
}
