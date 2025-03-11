package dev.psegerfast.ikeashoppingapp.product.data.mappers

import dev.psegerfast.ikeashoppingapp.product.data.dto.MoneyDto
import dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo.ChairInfoDto
import dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo.CouchInfoDto
import dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo.ProductDto
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.product.domain.Product

fun ProductDto.toProduct(): Product = when(type) {
    "couch" -> Product.Couch(
        id = this.id,
        name = this.name,
        price = this.price.toMoney(),
        imageUrl = this.imageUrl,
        color = this.info.color,
        seats = (this.info as CouchInfoDto).numberOfSeats,
    )
    "chair" -> Product.Chair(
        id = this.id,
        name = this.name,
        price = this.price.toMoney(),
        imageUrl = this.imageUrl,
        color = this.info.color,
        material = (this.info as ChairInfoDto).material,
    )
    else -> throw IllegalArgumentException("Unsupported product type: $type")
}

fun MoneyDto.toMoney(): Money = Money(
    value = this.value,
    currency = this.currency
)
