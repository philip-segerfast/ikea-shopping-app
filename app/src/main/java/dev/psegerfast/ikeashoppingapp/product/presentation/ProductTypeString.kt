package dev.psegerfast.ikeashoppingapp.product.presentation

import dev.psegerfast.ikeashoppingapp.product.domain.Product

fun Product.type(): String = when(this) {
    is Product.Chair -> "chair"
    is Product.Couch -> "couch"
}