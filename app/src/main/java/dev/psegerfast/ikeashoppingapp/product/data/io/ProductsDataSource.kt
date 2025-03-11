package dev.psegerfast.ikeashoppingapp.product.data.io

import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.domain.Product

interface ProductsDataSource {
    /** @return A json string containing the products. */
    suspend fun getProducts(): Result<List<Product>, DataError.IO>
}