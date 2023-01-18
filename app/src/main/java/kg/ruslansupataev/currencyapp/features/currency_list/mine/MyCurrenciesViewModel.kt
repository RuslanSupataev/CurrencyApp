package kg.ruslansupataev.currencyapp.features.currency_list.mine

import kg.ruslansupataev.currencyapp.core.BaseStateViewModel
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kg.ruslansupataev.currencyapp.domain.usecases.ChangeCurrencyFavoriteStatus
import kg.ruslansupataev.currencyapp.domain.usecases.GetFavorites
import kg.ruslansupataev.currencyapp.features.currency_list.mine.state.MyCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_list.mine.state.MyCurrenciesFragmentState
import kotlinx.coroutines.flow.update

class MyCurrenciesViewModel(
    private val getFavorites: GetFavorites,
    private val changeCurrencyFavoriteStatus: ChangeCurrencyFavoriteStatus
) : BaseStateViewModel<MyCurrenciesFragmentState, MyCurrenciesEvent>(
    defaultState = MyCurrenciesFragmentState(
        listOf()
    )
) {
    override suspend fun handleIntent(intent: MyCurrenciesEvent) {
        when (intent) {
            is MyCurrenciesEvent.GetCurrencyRates -> fetchFavs()
            is MyCurrenciesEvent.ChangeFavoriteStatus -> deleteFromFavs(intent.model)
        }
    }

    private suspend fun fetchFavs() {
        handleResourceInFlow(getFavorites()) { data ->
            _state.update { it.copy(myCurrencies = data) }
        }
    }

    private suspend fun deleteFromFavs(model: CurrencyRate) {
        changeCurrencyFavoriteStatus(model)

        _state.update { state ->
            state.copy(myCurrencies = mutableListOf<CurrencyRate>().also {
                it.addAll(state.myCurrencies)
                it.remove(model)
            })
        }
    }
}