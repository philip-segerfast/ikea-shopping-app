package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateCartTotalUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): Flow<Money> {
        val productsInCartFlow = cartRepository
            .getProductsInCartFlow()
            .valueOrError {
                error("Failed to get products in cart: ${it.error}")
            }

        return productsInCartFlow.map { productBundles ->
            if(productBundles.isEmpty()) {
                return@map Money.ZERO
            }

            val currency = productBundles.first().product.price.currency
            var totalValue = 0.0

            // Ensure that all products in the cart have the same currency.
            productBundles.forEach { productBundle ->
                val price = productBundle.product.price
                check(price.currency == currency) {
                    "All products in cart must have the same currency. Currency mismatch: ${price.currency} != $currency"
                }

                totalValue += price.value * productBundle.quantity
            }

            return@map Money(totalValue, currency)
        }
    }
}
