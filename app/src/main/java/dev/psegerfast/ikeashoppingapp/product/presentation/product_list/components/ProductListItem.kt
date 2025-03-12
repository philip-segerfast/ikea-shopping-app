package dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaBlue
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.presentation.ListItemContainer
import dev.psegerfast.ikeashoppingapp.product.domain.Product

@Preview
@Composable
private fun ProductListItemPreview() {
    Column {
        ProductListItem(
            product = Product.Couch(
                id = 0,
                name = "Lidhult",
                price = Money(959.5, "kr"),
                imageUrl = "",
                color = "white",
                seats = 3,
            ),
            onAddToCart = {},
        )
        Spacer(Modifier.height(32.dp))
        ProductListItem(
            product = Product.Chair(
                id = 0,
                name = "Henriksdal",
                price = Money(1195.0, "kr"),
                imageUrl = "",
                color = "black",
                material = "wood with cover"
            ),
            onAddToCart = {},
        )

    }
}

@Composable
fun ProductListItem(
    product: Product,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItemContainer(modifier) {
        Row(
            Modifier.height(IntrinsicSize.Min).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProductPicture(
                modifier = Modifier.size(64.dp),
                product = product,
            )

            Spacer(Modifier.width(8.dp))

            ProductDescription(
                modifier = Modifier.weight(1f),
                product = product,
            )

            ProductPrice(product.price)

            IconButton(
                onClick = onAddToCart
            ) {
                Icon(
                    painterResource(R.drawable.round_add_shopping_cart_24),
                    contentDescription = stringResource(R.string.add_to_cart),
                    tint = IkeaBlue
                )
            }
        }
    }
}
