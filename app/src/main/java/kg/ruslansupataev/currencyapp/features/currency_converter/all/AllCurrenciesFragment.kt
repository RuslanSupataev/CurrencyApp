package kg.ruslansupataev.currencyapp.features.currency_converter.all

import kg.ruslansupataev.currencyapp.core.BaseFragmentViewBindingState
import kg.ruslansupataev.currencyapp.databinding.FragmentAllCurrenciesBinding
import kg.ruslansupataev.currencyapp.features.currency_converter.all.state.AllCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_converter.all.state.AllCurrenciesState

class AllCurrenciesFragment : BaseFragmentViewBindingState<
        AllCurrenciesState,
        AllCurrenciesEvent,
        FragmentAllCurrenciesBinding,
        AllCurrenciesViewModel>(
    FragmentAllCurrenciesBinding::inflate,
    AllCurrenciesViewModel::class.java
) {


}