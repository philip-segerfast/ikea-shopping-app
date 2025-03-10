package dev.psegerfast.ikeashoppingapp.product.data.dto

import dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo.ProductDto
import kotlinx.serialization.Serializable

@Serializable
data class ProductListDto(
    val products: List<ProductDto>
)