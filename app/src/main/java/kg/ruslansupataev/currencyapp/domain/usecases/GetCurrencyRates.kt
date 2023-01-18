package kg.ruslansupataev.currencyapp.domain.usecases

import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo

class GetCurrencyRates(
    private val repo: CurrencyRepo
) {
    operator fun invoke() = repo.getCurrencyRates()
}