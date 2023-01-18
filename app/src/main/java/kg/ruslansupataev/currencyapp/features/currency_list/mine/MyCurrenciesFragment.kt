package kg.ruslansupataev.currencyapp.features.currency_list.mine

import androidx.recyclerview.widget.ItemTouchHelper
import kg.ruslansupataev.currencyapp.core.BaseFragmentViewBindingState
import kg.ruslansupataev.currencyapp.core.ISearchableFragment
import kg.ruslansupataev.currencyapp.databinding.FragmentMyCurrenciesBinding
import kg.ruslansupataev.currencyapp.features.currency_list.mine.recycler.MyCurrenciesAdapter
import kg.ruslansupataev.currencyapp.features.currency_list.mine.recycler.SwipeToDeleteCallback
import kg.ruslansupataev.currencyapp.features.currency_list.mine.state.MyCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_list.mine.state.MyCurrenciesFragmentState
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyCurrenciesFragment : BaseFragmentViewBindingState<
        MyCurrenciesFragmentState,
        MyCurrenciesEvent,
        FragmentMyCurrenciesBinding,
        MyCurrenciesViewModel>(
    FragmentMyCurrenciesBinding::inflate
), ISearchableFragment {
    override val viewModel: MyCurrenciesViewModel by viewModel()

    private val adapter by lazy {
        MyCurrenciesAdapter()
    }

    private val swipeCallback by lazy {
        SwipeToDeleteCallback(
            requireContext()
        ) { pos ->
            viewModel.handleSideEffects(
                MyCurrenciesEvent.ChangeFavoriteStatus(
                    adapter.currentList[pos]
                )
            )
        }
    }

    override fun initialize() {
        super.initialize()
        getData()
        initUI()
        setObserves(viewModel.state)
    }

    private fun getData() {
        viewModel.handleSideEffects(MyCurrenciesEvent.GetCurrencyRates)
    }

    private fun initUI() {
        binding.recyclerView.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun setObserves(state: StateFlow<MyCurrenciesFragmentState>) {
        state.partialListener(adapter::modifyList) { it.myCurrencies }
    }

    override fun search(query: CharSequence?) {
        adapter.filter(query)
    }
}