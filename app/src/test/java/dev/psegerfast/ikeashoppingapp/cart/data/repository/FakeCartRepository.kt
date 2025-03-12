package dev.psegerfast.ikeashoppingapp.cart.data.repository

import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.EmptyResult
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeCartRepository : CartRepository {
    private val _productsInCart = MutableStateFlow(persistentListOf<ProductBundle>())

    override suspend fun addToCart(product: Product): EmptyResult<DataError.Local> {
        // Get current bundle if there is one or create a new one with [product]
        val current = _productsInCart.value
            .find { it.product == product }
            ?.also { currentBundle -> _productsInCart.update { it.remove(currentBundle) } }
            ?: ProductBundle(product, 0)

        val newBundle = current.copy(
            quantity = current.quantity + 1
        )

        _productsInCart.update { it.add(newBundle) }

        return Result.Success(Unit)
    }

    override suspend fun subtractItemFromCart(product: Product): EmptyResult<DataError.Local> {
        val current = _productsInCart.value
            .find { it.product == product }
            ?.also { currentBundle -> _productsInCart.update { it.remove(currentBundle) } }
            ?: ProductBundle(product, 0)

        if(current.quantity == 1) {
            return clearProductFromCart(product)
        }

        val newBundle = current.copy(
            quantity = current.quantity - 1
        )
        _productsInCart.update { it.add(newBundle) }

        return Result.Success(Unit)
    }

    override suspend fun clearProductFromCart(product: Product): EmptyResult<DataError.Local> {
        _productsInCart.update { cart ->
            cart.removeAll { it.product == product }
        }

        return Result.Success(Unit)
    }

    override suspend fun clearCart(): EmptyResult<DataError.Local> {
        _productsInCart.update { it.clear() }
        return Result.Success(Unit)
    }

    override suspend fun getProductsInCartFlow(): Result<Flow<List<ProductBundle>>, DataError> {
        return Result.Success(_productsInCart.asStateFlow())
    }

}