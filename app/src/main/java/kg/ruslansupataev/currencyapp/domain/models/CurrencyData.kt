package kg.ruslansupataev.currencyapp.domain.models

data class CurrencyRate(
    val currency: String,
    val rate: Double,
    val isFavorite: Boolean = false
)