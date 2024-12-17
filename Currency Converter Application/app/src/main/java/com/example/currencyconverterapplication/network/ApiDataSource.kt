package com.example.currencyconverterapplication.network

import com.example.currencyconverterapplication.services.CurrencyApiService
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: CurrencyApiService) {
    suspend fun getExchangeRates(accessKey: String) = apiService.getExchangeRates(accessKey)
}