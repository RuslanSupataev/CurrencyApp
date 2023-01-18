package kg.ruslansupataev.currencyapp.features.currency_converter.all.state

import kg.ruslansupataev.currencyapp.core.BaseUIState
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate

data class AllCurrenciesState(
    val allCurrencies: List<CurrencyRate>
): BaseUIState()

sealed class AllCurrenciesEvent {
    object GetCurrencyRates: AllCurrenciesEvent()
    data class ChangeFavoriteStatus(val model: CurrencyRate): AllCurrenciesEvent()
}