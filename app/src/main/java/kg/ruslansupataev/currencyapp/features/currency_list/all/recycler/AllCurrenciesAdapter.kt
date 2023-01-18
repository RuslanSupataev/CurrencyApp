package kg.ruslansupataev.currencyapp.features.currency_list.all.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kg.ruslansupataev.currencyapp.databinding.ItemCurrencyBinding
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import java.util.*

class AllCurrenciesAdapter(
    private val itemFavorite: (model: CurrencyRate) -> Unit,
    private val itemClicked: (model: CurrencyRate) -> Unit
) : ListAdapter<CurrencyRate, CurrencyViewHolder>(
    object : DiffUtil.ItemCallback<CurrencyRate>() {
        override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) =
            oldItem.currency == newItem.currency

        override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) =
            oldItem == newItem
    }
) {
    companion object {
        const val CURRENCY_TYPE = 2333
    }

    private var unfilteredList: List<CurrencyRate> = listOf()
    private var lastFilter = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CurrencyViewHolder(
        ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        itemFavorite,
        itemClicked
    )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return CURRENCY_TYPE
    }

    fun filter(query: CharSequence?) {
        lastFilter = query.toString()
        val list = mutableListOf<CurrencyRate>()

        // perform the data filtering
        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.currency.lowercase(Locale.getDefault()).contains(
                    query.toString()
                        .lowercase(Locale.getDefault())
                )
            })
        } else {
            list.addAll(unfilteredList)
        }

        submitList(list)
    }

    fun modifyList(list: List<CurrencyRate>) {
        unfilteredList = list
        filter(lastFilter)
    }

}