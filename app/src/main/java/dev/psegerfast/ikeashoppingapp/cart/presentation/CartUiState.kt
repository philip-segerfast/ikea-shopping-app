package dev.psegerfast.ikeashoppingapp.cart.presentation

import androidx.annotation.StringRes
import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.core.domain.Money

data class CartUiState(
    val products: List<ProductBundle> = emptyList(),
    val itemCount: Int = 0,
    val valueTotal: Money = Money.ZERO,
    @StringRes val error: Int? = null,
)