package kg.ruslansupataev.currencyapp.core

import android.content.Context
import android.os.Build

fun provideCurrentLocale(context: Context): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        context.resources.configuration.locales.get(0)
    } else{
        //noinspection deprecation
        context.resources.configuration.locale
    }.country
}
