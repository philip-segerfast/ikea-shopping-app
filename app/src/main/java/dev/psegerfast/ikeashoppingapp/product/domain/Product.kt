package dev.psegerfast.ikeashoppingapp.product.domain

import dev.psegerfast.ikeashoppingapp.core.domain.Money

sealed interface Product {
    val id: Int
    val name: String
    val price: Money
    val imageUrl: String
    val color: String

    data class Couch(
        override val id: Int,
        override val name: String,
        override val price: Money,
        override val imageUrl: String,
        override val color: String,
        val seats: Int,
    ) : Product

    data class Chair(
        override val id: Int,
        override val name: String,
        override val price: Money,
        override val imageUrl: String,
        override val color: String,
        val material: String,
    ) : Product
}
