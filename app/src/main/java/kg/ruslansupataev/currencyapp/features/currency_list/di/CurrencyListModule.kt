package kg.ruslansupataev.currencyapp.features.currency_list.di

import kg.ruslansupataev.currencyapp.features.currency_list.all.AllCurrenciesViewModel
import kg.ruslansupataev.currencyapp.features.currency_list.mine.MyCurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCurrencyList = module {
    viewModel { AllCurrenciesViewModel(get(), get()) }
    viewModel { MyCurrenciesViewModel(get(), get()) }
}