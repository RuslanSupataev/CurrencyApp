package kg.ruslansupataev.currencyapp.features.currency_converter

import android.view.ContextMenu
import android.view.View
import androidx.core.widget.doOnTextChanged
import kg.ruslansupataev.currencyapp.core.BaseFragmentViewBindingState
import kg.ruslansupataev.currencyapp.core.IFragmentWithoutBottomBar
import kg.ruslansupataev.currencyapp.databinding.FragmentCurrencyConvertBinding
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyConverterFragment : BaseFragmentViewBindingState<
        CurrencyConverterState,
        CurrencyConverterEvents,
        FragmentCurrencyConvertBinding,
        CurrencyConverterViewModel>(
    FragmentCurrencyConvertBinding::inflate
), IFragmentWithoutBottomBar {
    override val viewModel: CurrencyConverterViewModel by viewModel()

    override fun initialize() {
        super.initialize()
        setListeners()
        setObserves(viewModel.state)
    }

    private fun setListeners() {
        binding.editText.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                val amount = text.toString().toDoubleOrNull()
                if (amount == null) {
                    binding.editText.error = "only numbers are allowed to use"
                } else {
                    viewModel.handleSideEffects(
                        CurrencyConverterEvents.Convert(
                            "EUR",
                            "USD",
                            amount
                        )
                    )
                }
            } else {
                binding.tvUsdResult.text = ""
            }

        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu.clear()
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    private fun setObserves(state: StateFlow<CurrencyConverterState>) {
        state.partialListener(this::setAmount) { it.rate }
    }

    private fun setAmount(amount: Double) {
        binding.tvUsdResult.text = amount.toString()
    }
}