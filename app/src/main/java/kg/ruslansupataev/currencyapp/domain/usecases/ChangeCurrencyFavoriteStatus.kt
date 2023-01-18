package kg.ruslansupataev.currencyapp.domain.usecases

import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo

class ChangeCurrencyFavoriteStatus(
    private val repo: CurrencyRepo
) {
    suspend operator fun invoke(model: CurrencyRate) = repo.changeFavoriteStatus(currency = model)
}