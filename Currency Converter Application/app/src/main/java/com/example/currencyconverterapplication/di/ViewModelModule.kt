package com.example.currencyconverterapplication.di

import androidx.lifecycle.ViewModel
import com.example.currencyconverterapplication.view_model.CurrencyViewModel
import com.example.currencyconverterapplication.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel
}