package kg.ruslansupataev.currencyapp.data.api

import androidx.room.Transaction
import kg.ruslansupataev.currencyapp.core.Resource
import kg.ruslansupataev.currencyapp.data.general.BaseRepo
import kg.ruslansupataev.currencyapp.data.local.CurrencyDao
import kg.ruslansupataev.currencyapp.data.local.CurrencyEntity
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo
import kotlinx.coroutines.flow.Flow

class CurrencyRepoImpl(
    private val api: ExchangeRateAPI,
    private val dao: CurrencyDao
) : BaseRepo(), CurrencyRepo {

    @Transaction
    suspend fun insertOrDeleteCurrency(currency: CurrencyEntity) {
        val existingCurrency = dao.getCurrency(currency.code)
        if (existingCurrency == null) {
            dao.insert(currency)
        } else {
            dao.deleteCurrency(currency.code)
        }
    }

    override fun getCurrencyRates(): Flow<Resource<List<CurrencyRate>>> = doRequest {
        // getting json from api
        val response = api.getLatestRates()
        val json = response.body()

        // checking if request went successfully
        if (json != null && response.isSuccessful && response.code() in 200..300) {
            // getting favorites to change isFavorite field
            val favorites = dao.getFavorites()

            // searching for sum json 'rates' in it
            val ratesJson = json.getAsJsonObject("rates")

            val ratesList = mutableListOf<CurrencyRate>()
            // iterate each property of ratesJson to create list
            for (entry in ratesJson.entrySet()) {
                val currency = entry.key
                val value = entry.value.asDouble
                val isFavorite = favorites.contains(CurrencyEntity(currency, value))

                ratesList.add(CurrencyRate(currency, value, isFavorite))
            }
            // mapping data for UI

            emit(Resource.Success(data = ratesList.sortedBy { it.currency }))
        } else {
            emit(Resource.Error(message = response.message()))
        }

    }

    override fun convertCurrency(from: String, to: String, amount: Double) = doRequest {
        val response = api.convert(from, to, amount)
        val data = response.body()

        // checking if request went successfully
        if (data != null && response.isSuccessful && response.code() in 200..300 && data.success) {
            emit(Resource.Success(data.result))
        } else {
            emit(Resource.Error(message = response.message()))
        }
    }

    override suspend fun changeFavoriteStatus(currency: CurrencyRate) {
        insertOrDeleteCurrency(CurrencyEntity(currency.currency, currency.rate))
    }

    override fun getFavorites(): Flow<Resource<List<CurrencyRate>>> = doRequest {
        getCurrencyRates().collect { resource ->
            if (resource is Resource.Success) {
                val favorites = dao.getFavorites()
                val favoritesFromApi = mutableListOf<CurrencyRate>()

                resource.data.forEach {
                    if (favorites.contains(CurrencyEntity(it.currency, it.rate))) {
                        favoritesFromApi.add(it)
                    }
                }

                emit(Resource.Success(favoritesFromApi))
            } else if (resource is Resource.Error) {
                emit(resource)
            }
        }
    }
}