package kg.ruslansupataev.currencyapp.features.currency_list.mine.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import kg.ruslansupataev.currencyapp.databinding.ItemFavCurrencyBinding
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate

class MyCurrencyViewHolder(
    private val binding: ItemFavCurrencyBinding
): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(model: CurrencyRate) {
        binding.currency.text = "${model.currency} (${model.rate} in EUR)"
    }
}