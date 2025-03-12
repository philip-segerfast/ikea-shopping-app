package dev.psegerfast.ikeashoppingapp.product.data.io

import android.content.Context
import android.content.res.Resources
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.core.domain.onSuccess
import dev.psegerfast.ikeashoppingapp.core.domain.valueOrError
import dev.psegerfast.ikeashoppingapp.product.data.dto.ProductListDto
import dev.psegerfast.ikeashoppingapp.product.data.mappers.toProduct
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class JsonProductsDataSource(
    private val context: Context,
    private val json: Json,
): ProductsDataSource {

    private val cachedProducts = mutableListOf<Product>()

    override suspend fun getProducts(): Result<List<Product>, DataError.IO> {
        if(cachedProducts.isNotEmpty()) return Result.Success(cachedProducts.toList())

        val result = parseProductsFromJson()
        result.onSuccess { products ->
            cachedProducts.addAll(products)
        }

        return result
    }

    private suspend fun parseProductsFromJson(): Result<List<Product>, DataError.IO> {
        val productsJson = readJson().valueOrError { return it }
        val productDtos = try {
            json.decodeFromString(ProductListDto.serializer(), productsJson).products
        } catch (e: Exception) {
            return Result.Error(DataError.IO.Unknown(e))
        }
        return Result.Success(productDtos.map { it.toProduct() })
    }

    private suspend fun readJson(): Result<String, DataError.IO> = withContext(Dispatchers.IO) {
        try {
            context
                .resources
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