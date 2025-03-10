package dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo

import kotlinx.serialization.Serializable

@Serializable
data class CouchInfoDto(
    val numberOfSeats: Int,
    override val color: String
): ProductInfoDto
