package kg.ruslansupataev.currencyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_favorite_table")
data class CurrencyEntity(
    @PrimaryKey
    val code: String,
    val rate: Double,
)