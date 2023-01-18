package kg.ruslansupataev.currencyapp.data.api

import com.google.gson.JsonObject
import kg.ruslansupataev.currencyapp.data.model.ConvertResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateAPI {

    @GET("latest")
    suspend fun getLatestRates(): Response<JsonObject>

    @GET("convert")
    suspend fun convert(
        @Query("from") fromCurrency: String,
        @Query("to") toCurrency: String,
        @Query("amount") amount: Double
    ): Response<ConvertResultDto>
}