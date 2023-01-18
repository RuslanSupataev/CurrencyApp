package kg.ruslansupataev.currencyapp.data.local

import android.content.Context
import androidx.room.Room

object CurrencyDatabaseProvider {
    fun getDatabase(context: Context): CurrencyDatabase {
        return Room.databaseBuilder(context, CurrencyDatabase::class.java, "currency_db")
            .build()
    }

    fun getCurrencyDao(database: CurrencyDatabase) = database.currencyDao
}