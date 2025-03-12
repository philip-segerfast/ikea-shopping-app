package dev.psegerfast.ikeashoppingapp.cart.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_products")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = false)
    val productId: Int,
    val quantity: Int,
)