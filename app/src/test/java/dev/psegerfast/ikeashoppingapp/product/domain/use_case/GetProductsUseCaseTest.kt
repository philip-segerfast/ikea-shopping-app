package dev.psegerfast.ikeashoppingapp.product.domain.use_case

import dev.psegerfast.ikeashoppingapp.core.domain.onError
import dev.psegerfast.ikeashoppingapp.core.domain.onSuccess
import dev.psegerfast.ikeashoppingapp.product.data.repository.FakeProductRepository
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.fail

class GetProductsUseCaseTest {
    private lateinit var productsRepository: FakeProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        productsRepository = FakeProductRepository()
        getProductsUseCase = GetProductsUseCase(
            productsRepository
        )
    }

    @Test
    fun testGetProducts() = runTest {
        val products = getProductsUseCase()
        products.onError {
            fail("Failed to get products: $it")
        }
        products.onSuccess { products ->
            assert(products.isNotEmpty())
        }
    }
}