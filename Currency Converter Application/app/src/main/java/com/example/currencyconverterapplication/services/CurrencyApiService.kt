package com.example.currencyconverterapplication.services

import com.example.currencyconverterapplication.helper.Endpoint
import com.example.currencyconverterapplication.model.ExchangeRateResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET(Endpoint.LATEST_URL)
    suspend fun getExchangeRates(@Query("access_key") accessKey: String): Response<ExchangeRateResponseModel>
}