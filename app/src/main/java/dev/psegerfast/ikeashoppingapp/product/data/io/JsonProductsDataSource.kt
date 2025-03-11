package dev.psegerfast.ikeashoppingapp.product.data.io

import android.content.res.Resources
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrNothing
import dev.psegerfast.ikeashoppingapp.product.data.dto.ProductListDto
import dev.psegerfast.ikeashoppingapp.product.data.mappers.toProduct
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class JsonProductsDataSource(
    private val resources: Resources,
    private val json: Json,
): ProductsDataSource {

    override suspend fun getProducts(): Result<List<Product>, DataError.IO> = parseProductsFromJson()

    private suspend fun parseProductsFromJson(): Result<List<Product>, DataError.IO> {
        val productsJson = readJson().valueOrNothing { return it }
        val productDtos = json.decodeFromString(ProductListDto.serializer(), productsJson).products
        return Result.Success(productDtos.map { it.toProduct() })
    }

    private suspend fun readJson(): Result<String, DataError.IO> = withContext(Dispatchers.IO) {
        try {
            resources
                .openRawResource(R.raw.products)
                .bufferedReader()
                .use { it.readText() }
                .let { Result.Success(it) }
        } catch (e: Resources.NotFoundException) {
            Result.Error(DataError.IO.FileNotFound(e))
        } catch (e: Exception) {
            Result.Error(DataError.IO.Unknown(e))
        }
    }
}