package kg.ruslansupataev.currencyapp.domain.usecases

import kg.ruslansupataev.currencyapp.domain.repo_apis.CurrencyRepo

class GetFavorites(
    private val repo: CurrencyRepo
){
    operator fun invoke() = repo.getFavorites()
}