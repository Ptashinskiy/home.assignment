package com.example.home.assignment.service;

public record CurrencyRate(String src,
                           String dst,
                           String at,
                           Double value) {
}
