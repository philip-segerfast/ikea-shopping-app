package dev.psegerfast.ikeashoppingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.psegerfast.ikeashoppingapp.core.domain.map
import dev.psegerfast.ikeashoppingapp.core.domain.onError
import dev.psegerfast.ikeashoppingapp.core.domain.onSuccess
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrNothing
import dev.psegerfast.ikeashoppingapp.product.data.io.JsonProductsDataSource
import dev.psegerfast.ikeashoppingapp.product.data.repository.DefaultProductRepository
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: ProductRepository = DefaultProductRepository(
        productsDataSource = JsonProductsDataSource(
            resources = application.resources,
            json = Json {
                this.ignoreUnknownKeys = true
            }
        )
    )

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    suspend fun fetchProducts() {
        repo
            .fetchProducts()
            .onSuccess {
                _products.value = it
            }.onError {
                _products.value = emptyList()
            }
    }

}