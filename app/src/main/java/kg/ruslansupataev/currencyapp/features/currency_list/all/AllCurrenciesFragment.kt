package kg.ruslansupataev.currencyapp.features.currency_list.all

import androidx.navigation.fragment.findNavController
import kg.ruslansupataev.currencyapp.R
import kg.ruslansupataev.currencyapp.core.BaseFragmentViewBindingState
import kg.ruslansupataev.currencyapp.core.ISearchableFragment
import kg.ruslansupataev.currencyapp.databinding.FragmentAllCurrenciesBinding
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import kg.ruslansupataev.currencyapp.features.currency_list.all.recycler.AllCurrenciesAdapter
import kg.ruslansupataev.currencyapp.features.currency_list.all.state.AllCurrenciesEvent
import kg.ruslansupataev.currencyapp.features.currency_list.all.state.AllCurrenciesState
import kg.ruslansupataev.currencyapp.features.currency_list.all.recycler.HolderPrefetcher
import kg.ruslansupataev.currencyapp.features.currency_list.all.recycler.PrefetchRecycledViewPool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCurrenciesFragment : BaseFragmentViewBindingState<
        AllCurrenciesState,
        AllCurrenciesEvent,
        FragmentAllCurrenciesBinding,
        AllCurrenciesViewModel>(
    FragmentAllCurrenciesBinding::inflate), ISearchableFragment {

    private val adapter: AllCurrenciesAdapter by lazy {
        AllCurrenciesAdapter(itemFavorite = this::onFavoriteClick, itemClicked = this::onItemClick)
    }
    private val coroutineScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private lateinit var viewPool: PrefetchRecycledViewPool

    override fun initialize() {
        super.initialize()
        getData()
        initUI()
        setObserves(viewModel.state)
    }

    private fun getData() {
        viewModel.handleSideEffects(AllCurrenciesEvent.GetCurrencyRates)
    }

    private fun initUI() {
        binding.recyclerView.adapter = adapter

        viewPool = PrefetchRecycledViewPool(
            requireActivity(),
            coroutineScope
        ).apply {
            prepare()
        }
        binding.recyclerView.setRecycledViewPool(viewPool)
        prefetchItems(viewPool)
    }

    private fun prefetchItems(holderPrefetcher: HolderPrefetcher) {
        holderPrefetcher.setViewsCount(AllCurrenciesAdapter.CURRENCY_TYPE, 100) { fakeParent, viewType ->
            adapter.onCreateViewHolder(fakeParent, viewType)
        }
    }

    private fun setObserves(state: StateFlow<AllCurrenciesState>) {
        state.partialListener(adapter::modifyList) { it.allCurrencies }
    }

    override fun search(query: CharSequence?) {
        adapter.filter(query)
    }

    private fun onFavoriteClick(model: CurrencyRate) {
        viewModel.handleSideEffects(AllCurrenciesEvent.ChangeFavoriteStatus(model))
    }

    private fun onItemClick(model: CurrencyRate) {
        findNavController().navigate(R.id.action_allCurrenciesFragment_to_currencyConverterFragment)
    }

    override val viewModel: AllCurrenciesViewModel by viewModel()
}