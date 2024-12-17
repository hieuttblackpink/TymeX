package com.example.currencyconverterapplication.helper

import com.example.currencyconverterapplication.BuildConfig

class Endpoint {
    companion object {
        //Base URL
        const val BASE_URL = "https://api.exchangeratesapi.io/v1/"
        const val API_KEY = BuildConfig.API_KEY

        //Sub URL
        const val LATEST_URL = "latest"
        const val CONVERT_URL = "convert"
    }
}