package dev.psegerfast.ikeashoppingapp.cart.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_products WHERE productId = :productId")
    suspend fun getProduct(productId: Int): CartProductEntity?

    @Upsert
    suspend fun upsert(item: CartProductEntity)

    @Query("DELETE FROM cart_products WHERE productId = :productId")
    suspend fun delete(productId: Int)

    @Query("DELETE FROM cart_products")
    suspend fun deleteAll()

    @Query("SELECT * FROM cart_products")
    fun getProductsInCartFlow(): Flow<List<CartProductEntity>>

}