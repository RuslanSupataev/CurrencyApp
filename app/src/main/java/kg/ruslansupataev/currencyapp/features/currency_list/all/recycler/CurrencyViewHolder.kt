package kg.ruslansupataev.currencyapp.features.currency_list.all.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import kg.ruslansupataev.currencyapp.R
import kg.ruslansupataev.currencyapp.databinding.ItemCurrencyBinding
import kg.ruslansupataev.currencyapp.domain.models.CurrencyRate

class CurrencyViewHolder(
    private val binding: ItemCurrencyBinding,
    private val favoriteOnClick: (model: CurrencyRate) -> Unit,
    private val itemClicked: (model: CurrencyRate) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(model: CurrencyRate) {
        binding.run {
            currency.text = "${model.currency} (${model.rate} in EUR)"
            button.setText(
                if (model.isFavorite) R.string.remove
                else R.string.add
            )
            button.setOnClickListener { favoriteOnClick(model) }
            root.setOnClickListener { itemClicked(model) }
        }

    }

}