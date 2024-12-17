package com.example.currencyconverterapplication.network

import com.example.currencyconverterapplication.services.CurrencyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private final val BASE_URL = "https://api.exchangeratesapi.io/v1/"

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    public val apiService: CurrencyApiService by lazy {
        instance.create(CurrencyApiService::class.java)
    }
}