package kg.ruslansupataev.currencyapp.features.currency_converter

import kg.ruslansupataev.currencyapp.core.BaseStateViewModel
import kg.ruslansupataev.currencyapp.domain.usecases.GetConvertCurrency
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.update

class CurrencyConverterViewModel(
    private val convertCurrency: GetConvertCurrency
): BaseStateViewModel<CurrencyConverterState, CurrencyConverterEvents>(
    CurrencyConverterState(0.0)
) {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var currentRequest: Deferred<Unit>? = null

    override suspend fun handleIntent(intent: CurrencyConverterEvents) {
        when(intent) {
            is CurrencyConverterEvents.Convert -> convert(intent.from, intent.to, intent.amount)
        }
    }

    private suspend fun convert(from: String, to: String, amount: Double) {
        currentRequest?.cancel()
        currentRequest = scope.async {
            handleResourceInFlow(convertCurrency(from, to, amount)) { rate ->
                _state.update { it.copy(rate = rate) }
            }
        }
    }
}