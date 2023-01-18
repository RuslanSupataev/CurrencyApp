package kg.ruslansupataev.currencyapp.data.di

import kg.ruslansupataev.currencyapp.core.provideCurrentLocale
import kg.ruslansupataev.currencyapp.data.api.CurrencyRepoImpl
import kg.ruslansupataev.currencyapp.data.api.provideExchangeRateAPI
import kg.ruslansupataev.currencyapp.data.api.provideOkHttpClient
import kg.ruslansupataev.currencyapp.data.api.provideRetrofit
import kg.ruslansupataev.currencyapp.data.local.CurrencyDatabaseProvider
import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get()) }
    factory { provideExchangeRateAPI(get()) }
    factory { CurrencyDatabaseProvider.getDatabase(androidContext()) }

    single<CurrencyRepo> { CurrencyRepoImpl(get(), get()) }
    single { CurrencyDatabaseProvider.getCurrencyDao(get()) }
}