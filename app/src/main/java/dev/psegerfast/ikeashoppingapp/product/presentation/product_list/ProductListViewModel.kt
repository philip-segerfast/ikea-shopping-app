package dev.psegerfast.ikeashoppingapp.product.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.psegerfast.ikeashoppingapp.core.domain.onError
import dev.psegerfast.ikeashoppingapp.core.domain.onSuccess
import dev.psegerfast.ikeashoppingapp.core.presentation.stringResource
import dev.psegerfast.ikeashoppingapp.product.domain.use_case.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState = _productListState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() = viewModelScope.launch {
        getProductsUseCase()
            .onSuccess { products ->
                _productListState.update { current ->
                    current.copy(
                        products = products,
                        error = null,
                    )
                }
            }
            .onError { error ->
                _productListState.update { current ->
                    current.copy(
                        products = emptyList(),
                        error = error.stringResource(),
                    )
                }
            }
    }

}