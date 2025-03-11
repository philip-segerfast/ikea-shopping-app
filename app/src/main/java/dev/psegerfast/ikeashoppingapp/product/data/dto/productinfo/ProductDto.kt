package dev.psegerfast.ikeashoppingapp.product.data.dto.productinfo

import dev.psegerfast.ikeashoppingapp.product.data.dto.MoneyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ProductDto {
    abstract val id: Int
    abstract val name: String
    abstract val price: MoneyDto
    abstract val imageUrl: String
    abstract val type: String
    abstract val info: ProductInfoDto

    @Serializable
    @SerialName("chair")
    data class ChairDto(
        override val id: Int,
        override val name: String,
        override val price: MoneyDto,
        override val info: ChairInfoDto,
        override val imageUrl: String,
        override val type: String,
    ) : ProductDto()

    @Serializable
    @SerialName("couch")
    data class CouchDto(
        override val id: Int,
        override val name: String,
        override val price: MoneyDto,
        override val info: CouchInfoDto,
        override val imageUrl: String,
        override val type: String,
    ) : ProductDto()

}