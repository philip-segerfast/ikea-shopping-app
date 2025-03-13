package dev.psegerfast.ikeashoppingapp.cart.presentation

import dev.psegerfast.ikeashoppingapp.product.domain.Product

sealed interface CartAction {
    data class AddToCart(val product: Product) : CartAction
    data class RemoveSingleFromCart(val product: Product) : CartAction
    data class ClearFromCart(val product: Product) : CartAction
    data object ClearCart : CartAction
}