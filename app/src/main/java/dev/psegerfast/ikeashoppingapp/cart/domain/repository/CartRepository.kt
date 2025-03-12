package dev.psegerfast.ikeashoppingapp.cart.domain.repository

import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.EmptyResult
import dev.psegerfast.ikeashoppingapp.core.domain.Error
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun addToCart(product: Product): EmptyResult<DataError.Local>

    suspend fun subtractItemFromCart(product: Product): EmptyResult<DataError.Local>

    /** Remove all products from cart of the given type */
    suspend fun clearProductFromCart(product: Product): EmptyResult<DataError.Local>

    suspend fun clearCart(): EmptyResult<DataError.Local>

    suspend fun getProductsInCartFlow(): Result<Flow<List<ProductBundle>>, DataError>
}