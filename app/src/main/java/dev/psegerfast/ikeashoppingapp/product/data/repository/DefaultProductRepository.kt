package dev.psegerfast.ikeashoppingapp.product.data.repository

import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.data.io.ProductsDataSource
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository

class DefaultProductRepository(
    private val productsDataSource: ProductsDataSource,
) : ProductRepository {

    override suspend fun fetchProducts(): Result<List<Product>, DataError.IO> {
        return productsDataSource.getProducts()
    }

}
