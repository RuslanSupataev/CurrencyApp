package kg.ruslansupataev.currencyapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(currency: CurrencyEntity)

    @Query("DELETE FROM currency_favorite_table WHERE code = :code")
    suspend fun deleteCurrency(code: String)

    @Query("SELECT * FROM currency_favorite_table")
    suspend fun getFavorites(): List<CurrencyEntity>

    @Query("SELECT * FROM currency_favorite_table WHERE code = :code")
    suspend fun getCurrency(code: String): CurrencyEntity?
}
