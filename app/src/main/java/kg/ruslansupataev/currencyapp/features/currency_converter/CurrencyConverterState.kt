package kg.ruslansupataev.currencyapp.features.currency_converter

import kg.ruslansupataev.currencyapp.core.BaseUIState

data class CurrencyConverterState(
    val rate: Double
) : BaseUIState()

sealed class CurrencyConverterEvents {
    data class Convert(val from: String, val to: String, val amount: Double) :
        CurrencyConverterEvents()
}