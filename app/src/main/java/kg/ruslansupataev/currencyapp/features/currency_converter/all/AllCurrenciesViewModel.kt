package kg.ruslansupataev.currencyapp.features.currency_converter.all

import kg.ruslansupataev.currencyapp.core.BaseStateViewModel
import kg.ruslansupataev.currencyapp.features.currency_converter.all.state.AllCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_converter.all.state.AllCurrenciesState

class AllCurrenciesViewModel: BaseStateViewModel<AllCurrenciesState, AllCurrenciesEvent>(
    defaultState = AllCurrenciesState(
        listOf()
    )
) {
}