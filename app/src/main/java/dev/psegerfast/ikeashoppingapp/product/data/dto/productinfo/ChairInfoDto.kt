package dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo

import kotlinx.serialization.Serializable

@Serializable
data class ChairInfoDto(
    val material: String,
    override val color: String
): ProductInfoDto
