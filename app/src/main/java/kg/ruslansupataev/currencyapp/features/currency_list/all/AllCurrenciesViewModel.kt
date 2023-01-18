package kg.ruslansupataev.currencyapp.features.currency_list.all

import kg.ruslansupataev.currencyapp.core.BaseStateViewModel
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kg.ruslansupataev.currencyapp.domain.usecases.ChangeCurrencyFavoriteStatus
import kg.ruslansupataev.currencyapp.domain.usecases.GetCurrencyRates
import kg.ruslansupataev.currencyapp.features.currency_list.all.state.AllCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_list.all.state.AllCurrenciesState
import kotlinx.coroutines.flow.update

class AllCurrenciesViewModel(
    private val getCurrencyRates: GetCurrencyRates,
    private val changeCurrencyFavoriteStatus: ChangeCurrencyFavoriteStatus,
) : BaseStateViewModel<AllCurrenciesState, AllCurrenciesEvent>(
    defaultState = AllCurrenciesState(
        listOf()
    )
) {
    override suspend fun handleIntent(intent: AllCurrenciesEvent) {
        when (intent) {
            is AllCurrenciesEvent.GetCurrencyRates -> fetchData()
            is AllCurrenciesEvent.ChangeFavoriteStatus -> likeDislike(intent.model)
        }
    }

    private suspend fun likeDislike(model: CurrencyRate) {
        changeCurrencyFavoriteStatus(model)

        _state.update { allCurrenciesState ->
            allCurrenciesState.copy(allCurrencies = allCurrenciesState.allCurrencies.map {
                if (it == model) model.copy(isFavorite = !model.isFavorite)
                else it
            })
        }
    }

    private suspend fun fetchData() {
        handleResourceInFlow(getCurrencyRates()) { data ->
            _state.update { it.copy(allCurrencies = data) }
        }
    }
}