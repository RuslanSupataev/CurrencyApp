package kg.ruslansupataev.currencyapp.features.currency_list.mine.state

import kg.ruslansupataev.currencyapp.core.BaseUIState
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate

data class MyCurrenciesFragmentState(
    val myCurrencies: List<CurrencyRate>
): BaseUIState()

sealed class MyCurrenciesEvent {
    object GetCurrencyRates: MyCurrenciesEvent()
    data class ChangeFavoriteStatus(val model: CurrencyRate): MyCurrenciesEvent()
}