package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.product.domain.Product

class RemoveSingleProductFromCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(product: Product) {
        cartRepository.subtractItemFromCart(product)
    }
}