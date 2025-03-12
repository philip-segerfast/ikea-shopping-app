package dev.psegerfast.ikeashoppingapp.product.domain.use_case

import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository

class GetProductsUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Result<List<Product>, DataError.IO> {
        return productRepository.fetchProducts()
    }
}