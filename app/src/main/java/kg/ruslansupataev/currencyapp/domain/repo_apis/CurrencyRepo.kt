package kg.ruslansupataev.currencyapp.domain.repo_apis

import kg.ruslansupataev.currencyapp.core.Resource
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    /**
     * function return flow with [Resource] data of what is a
     * list of currency with their rate to EUR(?)
     */
    fun getCurrencyRates(): Flow<Resource<List<CurrencyRate>>>

    /**
     * converting currency to get current info about price
     *
     * @param from Currency Code which is to be converted
     * @param to Currency Code of currency you want [from] to be converted into
     * @param amount is just amount of [from]
     *
     * @return result price of [amount] of [from] in [to]
     */
    fun convertCurrency(from: String, to: String, amount: Double): Flow<Resource<Double>>

    /**
     * Add or remove from favorites.
     * If this model in favorites it will delete it from there.
     */
    suspend fun changeFavoriteStatus(currency: CurrencyRate)

    /**
     * Provides all favorite currencies
     */
    fun getFavorites(): Flow<Resource<List<CurrencyRate>>>
}