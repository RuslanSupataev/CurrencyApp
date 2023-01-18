package kg.ruslansupataev.currencyapp.features.currency_converter.di

import kg.ruslansupataev.currencyapp.features.currency_converter.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCurrencyConverterModule = module {
    viewModel { CurrencyConverterViewModel(get()) }
}