package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import kotlinx.coroutines.flow.Flow

class GetProductsInCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): Flow<List<ProductBundle>> {
        return cartRepository.getProductsInCartFlow().valueOrError {
            error("Failed to get products in cart: ${it.error}")
        }
    }
}