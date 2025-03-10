package dev.psegerfast.ikeashoppingapp.product.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoneyDto(
    val value: Double,
    val currency: String
)
