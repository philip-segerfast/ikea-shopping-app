package dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.ProductListState
import dev.psegerfast.ikeashoppingapp.product.presentation.type

private val fakeProductListItems = listOf(
    Product.Couch(
        id = 0,
        name = "Lidhult",
        price = Money(959.0, "kr"),
        imageUrl = "",
        color = "white",
        seats = 3,
    ),
    Product.Chair(
        id = 1,
        name = "Henriksdal",
        price = Money(959.0, "kr"),
        imageUrl = "",
        color = "white",
        material = "wood with cover"
    )
)

@Composable
fun ProductList(
    productListUiState: ProductListState,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        productListUiState.error?.let { error ->
            item {
                Text("Error: ${stringResource(error)}")
            }
        }
        items(
            items = productListUiState.products,
            key = { it.id },
            contentType = { it.type() }
        ) { item ->
            ProductListItem(
                product = item,
                onAddToCart = { onAddToCart(item) }
            )
        }
    }
}

@Preview
@Composable
private fun ProductListPreview(modifier: Modifier = Modifier) {
    ProductList(
        productListUiState = ProductListState(
            products = fakeProductListItems
        ),
        onAddToCart = {}
    )
}