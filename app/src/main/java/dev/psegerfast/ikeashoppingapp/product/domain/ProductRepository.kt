package dev.psegerfast.ikeashoppingapp.product.domain

import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result

interface ProductRepository {

    suspend fun fetchProducts(): Result<List<Product>, DataError.IO>

}