package com.example.home.assignment.dto;

public record CurrencyRate(String src,
                           String dst,
                           String at,
                           Double value) {
}
