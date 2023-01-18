package kg.ruslansupataev.currencyapp.domain.usecases

import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo

class GetConvertCurrency(
    private val repo: CurrencyRepo
) {
    operator fun invoke(from: String, to: String, amount: Double) =
        repo.convertCurrency(from, to, amount)
}