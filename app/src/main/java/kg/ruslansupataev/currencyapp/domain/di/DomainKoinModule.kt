package kg.ruslansupataev.currencyapp.domain.di

import kg.ruslansupataev.currencyapp.domain.usecases.ChangeCurrencyFavoriteStatus
import kg.ruslansupataev.currencyapp.domain.usecases.GetConvertCurrency
import kg.ruslansupataev.currencyapp.domain.usecases.GetCurrencyRates
import kg.ruslansupataev.currencyapp.domain.usecases.GetFavorites
import org.koin.dsl.module

val domainModule = module {
    single { GetCurrencyRates(get()) }
    single { GetConvertCurrency(get()) }
    single { ChangeCurrencyFavoriteStatus(get()) }
    single { GetFavorites(get()) }
}