package dev.psegerfast.ikeashoppingapp.cart.domain

import dev.psegerfast.ikeashoppingapp.product.domain.Product

data class ProductBundle(
    val product: Product,
    val quantity: Int,
)