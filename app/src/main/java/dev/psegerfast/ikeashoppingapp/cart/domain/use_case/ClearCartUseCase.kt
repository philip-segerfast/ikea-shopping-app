package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository

class ClearCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke() {
        cartRepository.clearCart()
    }
}