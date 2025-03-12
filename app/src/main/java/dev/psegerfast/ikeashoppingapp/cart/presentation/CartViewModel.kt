package dev.psegerfast.ikeashoppingapp.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.CartUseCases
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartUseCases: CartUseCases,
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartUiState())
    val cartState = _cartState.asStateFlow()

    init {
        observeCartState()
    }

    private fun observeCartState(): Job = viewModelScope.launch {
        launch {
            cartUseCases.calculateCartTotal().collect { total ->
                _cartState.update { cartState ->
                    cartState.copy(
                        valueTotal = total
                    )
                }
            }
        }
        launch {
            cartUseCases.getProductsInCartFlow().collect { products ->
                _cartState.update { cartState ->
                    cartState.copy(
                        products = products
                    )
                }
            }
        }
        launch {
            cartUseCases.calculateNumberOfItems().collect { productCount ->
                _cartState.update { cartState ->
                    cartState.copy(
                        itemCount = productCount
                    )
                }
            }
        }
    }

    fun onAction(action: CartAction) {
        when (action) {
            is CartAction.AddToCart -> addSingleProductToCart(action.product)
            is CartAction.RemoveSingleFromCart -> removeSingleProductFromCart(action.product)
            is CartAction.ClearFromCart -> clearProductFromCart(action.product)
            CartAction.ClearCart -> clearCart()
        }
    }

    private fun addSingleProductToCart(product: Product) {
        viewModelScope.launch {
            cartUseCases.addToCart(product)
        }
    }

    private fun removeSingleProductFromCart(product: Product) {
        viewModelScope.launch {
            cartUseCases.removeSingleFromCart(product)
        }
    }

    private fun clearProductFromCart(product: Product) {
        viewModelScope.launch {
            cartUseCases.clearProductFromCart(product)
        }
    }

    private fun clearCart() {
        viewModelScope.launch {
            cartUseCases.clearCart()
        }
    }

}