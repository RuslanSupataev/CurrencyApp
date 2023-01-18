package kg.ruslansupataev.currencyapp.features.currency_list.all.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

typealias HolderCreator = (fakeParent: ViewGroup, viewType: Int) -> RecyclerView.ViewHolder

interface HolderPrefetcher {
    fun setViewsCount(
        viewType: Int,
        count: Int,
        holderCreator: HolderCreator
    )
}