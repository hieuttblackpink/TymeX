package com.example.currencyconverterapplication.model

data class ExchangeRateResponseModel (
    var rates: Map<String, Double>,
    val base: String,
    val date: String,
    val success: Boolean,
    val timestamp: Int
)