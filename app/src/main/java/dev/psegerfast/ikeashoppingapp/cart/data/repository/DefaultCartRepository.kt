package dev.psegerfast.ikeashoppingapp.cart.data.repository

import dev.psegerfast.ikeashoppingapp.cart.data.database.CartDao
import dev.psegerfast.ikeashoppingapp.cart.data.database.CartProductEntity
import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.EmptyResult
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.core.domain.runCatchingDatabaseErrors
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class DefaultCartRepository(
    private val productRepository: ProductRepository,
    private val cartDao: CartDao,
) : CartRepository {
    override suspend fun addToCart(product: Product): EmptyResult<DataError.Local> = runCatchingDatabaseErrors {
        val productId = product.id
        val current = cartDao.getProduct(productId)
            ?.also {
                Timber.d("In database: $it")
            }
            ?: CartProductEntity(productId, 0)

        val newValue = current.copy(quantity = current.quantity + 1)
        cartDao.upsert(newValue)
    }

    override suspend fun subtractItemFromCart(product: Product): EmptyResult<DataError.Local> = runCatchingDatabaseErrors {
        val productId = product.id
        val current = cartDao.getProduct(productId) ?: return@runCatchingDatabaseErrors
        if(current.quantity == 1) return clearProductFromCart(product)
        cartDao.upsert(current.copy(quantity = current.quantity - 1))
        Result.Success(Unit)
    }

    override suspend fun clearProductFromCart(product: Product): EmptyResult<DataError.Local> = runCatchingDatabaseErrors {
        cartDao.delete(product.id)
    }

    override suspend fun clearCart(): EmptyResult<DataError.Local> = runCatchingDatabaseErrors {
        cartDao.deleteAll()
    }

    override suspend fun getProductsInCartFlow(): Result<Flow<List<ProductBundle>>, DataError> {
        val productsById = productRepository
            .fetchProducts()
            .valueOrError { error -> return error }
            .associateBy { it.id }

        val productsFlow = cartDao
            .getProductsInCartFlow()
            .map { cartProducts ->
                cartProducts.map { cartProduct ->
                    ProductBundle(
                        product = productsById[cartProduct.productId]!!,
                        quantity = cartProduct.quantity
                    )
                }
            }

        return Result.Success(productsFlow)
    }

}
