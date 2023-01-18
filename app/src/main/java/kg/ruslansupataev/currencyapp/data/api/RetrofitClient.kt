package kg.ruslansupataev.currencyapp.data.api

import kg.ruslansupataev.currencyapp.BuildConfig
import kg.ruslansupataev.currencyapp.data.general.MyHostnameVerifier
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * function for creating a Retrofit instance
 *
 * @param okHttpClient is OkHttpClient within configs to be added into retrofit
 * @return Retrofit instance
 */
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

/**
 * it's clearly simple function
 *
 * @return OkHttpClient
 */
fun provideOkHttpClient(currentLang: String? = null): OkHttpClient = OkHttpClient.Builder()
    .hostnameVerifier(MyHostnameVerifier())
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .addInterceptor {
        return@addInterceptor it.run {
            proceed(
                request()
                    .newBuilder()
                    //.addHeader("Accept-Language", currentLang)
                    .build()
            )
        }
    }
    .build()

/**
 * i don't know why would you need docs for this kind of method lol
 *
 * @param retrofit is retrofit for create AuthApi
 * @return AuthApi
 */
fun provideExchangeRateAPI(retrofit: Retrofit): ExchangeRateAPI =
    retrofit.create(ExchangeRateAPI::class.java)
