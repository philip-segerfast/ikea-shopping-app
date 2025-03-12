package dev.psegerfast.ikeashoppingapp.core.domain

data class Money(
    val value: Double,
    val currency: String,
) {
    companion object {
        const val DEFAULT_CURRENCY = "kr"
        val ZERO = Money(0.0, DEFAULT_CURRENCY)
    }
}