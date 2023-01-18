package kg.ruslansupataev.currencyapp

import android.app.Application
import kg.ruslansupataev.currencyapp.data.di.dataModule
import kg.ruslansupataev.currencyapp.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // start a KoinApplication in Global context
        startKoin {
            androidContext(this@CurrencyApp)
            // declare used modules
            modules(
                dataModule,
                domainModule
            )
        }
    }
}