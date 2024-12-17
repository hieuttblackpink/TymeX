package com.example.currencyconverterapplication.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapplication.helper.Resource
import com.example.currencyconverterapplication.helper.SingleLiveEvent
import com.example.currencyconverterapplication.model.ExchangeRateResponseModel
import dagger.MapKey
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) : ViewModel() {
    //cached
    private val _data = SingleLiveEvent<Resource<ExchangeRateResponseModel>>()

    //public
    val data get() = _data

    //Public function to get the result of conversion
    fun getConvertedData(apiKey: String) {
        viewModelScope.launch {
            currencyRepository.getExchangeRates(apiKey).collect {
                data.value = it
            }
        }
    }
}

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)