package dev.psegerfast.ikeashoppingapp

import dev.psegerfast.ikeashoppingapp.cart.data.repository.FakeCartRepository
import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.CalculateCartTotalUseCase
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import dev.psegerfast.ikeashoppingapp.product.data.repository.FakeProductRepository
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CartTests {

    private lateinit var calculateCartTotalUseCase: CalculateCartTotalUseCase
    private lateinit var productRepository: FakeProductRepository
    private lateinit var cartRepository: FakeCartRepository
    private val testProduct1 = Product.Couch(
        id = 0,
        name = "Comfy couch",
        price = Money(10_000.0, Money.DEFAULT_CURRENCY),
        imageUrl = "",
        color = "white",
        seats = 4,
    )
    private val testProduct2 = Product.Chair(
        id = 1,
        name = "Nice chair",
        price = Money(100.0, Money.DEFAULT_CURRENCY),
        imageUrl = "",
        color = "white",
        material = "wood"
    )

    // Utils

    private suspend fun getProducts(): List<ProductBundle> {
        return cartRepository
            .getProductsInCartFlow()
            .valueOrError { error("Failed to get products in cart: $it") }
            .first()
    }

    private suspend fun getTotalProductValue(): Money {
        return calculateCartTotalUseCase().first()
    }

    // Tests

    @Before
    fun setup() {
        cartRepository = FakeCartRepository()
        productRepository = FakeProductRepository()
        calculateCartTotalUseCase = CalculateCartTotalUseCase(cartRepository)
    }

    @Test
    fun `test cart is empty and total value is ZERO`() = runTest {
        assert(getProducts().isEmpty())
        assert(getTotalProductValue() == Money.ZERO)
    }

    @Test
    fun `test add single product to cart and check total value`() = runTest {
        // Add item to cart
        cartRepository.addToCart(testProduct1)
        // Check that item is in cart and quantity is 1
        getProducts().let { productsInCart ->
            val addedProductBundle = productsInCart.find { it.product == testProduct1 }
            check(addedProductBundle != null)
            assert(addedProductBundle.quantity == 1)
        }
        // Check that total value is 10_000
        assert(getTotalProductValue() == Money(10_000.0, Money.DEFAULT_CURRENCY))
    }

    @Test
    fun `test add multiple of same item to cart and check total value`() = runTest {
        repeat(5) {
            cartRepository.addToCart(testProduct1)
        }
        getProducts().let { productsInCart ->
            println("Products in cart: $productsInCart")
            val addedProductBundle = productsInCart.find { it.product == testProduct1 }
            check(addedProductBundle != null)
            assert(addedProductBundle.quantity == 5)
        }
        assert(getTotalProductValue() == Money(50_000.0, Money.DEFAULT_CURRENCY))
    }

    @Test
    fun `test add multiple items of different types to cart and check total value`() = runTest {
        cartRepository.addToCart(testProduct1)
        cartRepository.addToCart(testProduct2)
        getProducts().let { productsInCart ->
            val addedProductBundle1 = productsInCart.find { it.product == testProduct1 }
            val addedProductBundle2 = productsInCart.find { it.product == testProduct2 }
            check(addedProductBundle1 != null)
            check(addedProductBundle2 != null)
            assert(addedProductBundle1.quantity == 1)
            assert(addedProductBundle2.quantity == 1)
        }
        assert(getTotalProductValue() == Money(10100.0, Money.DEFAULT_CURRENCY))
    }

    @Test
    fun `test add and subtract item from cart and check total value`() = runTest {
        cartRepository.addToCart(testProduct1)
        cartRepository.addToCart(testProduct1)
        cartRepository.subtractItemFromCart(testProduct1)
        getProducts().let { productsInCart ->
            val addedProductBundle = productsInCart.find { it.product == testProduct1 }
            check(addedProductBundle != null)
            assert(addedProductBundle.quantity == 1)
        }
    }

    @Test
    fun `test add and clear cart and check total value`() = runTest {
        cartRepository.addToCart(testProduct1)
        cartRepository.addToCart(testProduct1)
        cartRepository.addToCart(testProduct2)
        cartRepository.clearCart()
        assert(getProducts().isEmpty())
        assert(getTotalProductValue() == Money.ZERO)
    }

}