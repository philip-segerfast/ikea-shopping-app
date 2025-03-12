package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateNumberOfItemsUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): Flow<Int> {
        val productsFlow = cartRepository.getProductsInCartFlow().valueOrError {
            error("Failed to get products: $it")
        }

        return productsFlow.map { productBundles ->
            var count = 0
            productBundles.forEach { productBundle ->
                count += productBundle.quantity
            }
            count
        }
    }
}