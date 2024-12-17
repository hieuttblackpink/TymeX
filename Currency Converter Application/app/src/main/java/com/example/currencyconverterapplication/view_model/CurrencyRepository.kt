package com.example.currencyconverterapplication.view_model

import com.example.currencyconverterapplication.helper.Resource
import com.example.currencyconverterapplication.model.ExchangeRateResponseModel
import com.example.currencyconverterapplication.network.ApiDataSource
import com.example.currencyconverterapplication.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {
    suspend fun getExchangeRates(apiKey: String) : Flow<Resource<ExchangeRateResponseModel>> {
        return flow {
            emit(safeApiCall { apiDataSource.getExchangeRates(apiKey) })
        }.flowOn(Dispatchers.IO)
    }
}