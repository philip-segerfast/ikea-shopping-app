package dev.psegerfast.ikeashoppingapp.product.presentation.product_list

import androidx.annotation.StringRes
import dev.psegerfast.ikeashoppingapp.product.domain.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
    @StringRes val error: Int? = null,
)
