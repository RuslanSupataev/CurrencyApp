package kg.ruslansupataev.currencyapp.features.currency_list.mine.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kg.ruslansupataev.currencyapp.databinding.ItemFavCurrencyBinding
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate
import java.util.*

class MyCurrenciesAdapter: ListAdapter<CurrencyRate, MyCurrencyViewHolder>(
    object : DiffUtil.ItemCallback<CurrencyRate>() {
        override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) =
            oldItem.currency == newItem.currency

        override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate) =
            oldItem == newItem
    }
) {

    private var unfilteredList: List<CurrencyRate> = listOf()
    private var lastFilter = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyCurrencyViewHolder(
        ItemFavCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyCurrencyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun filter(query: CharSequence?) {
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