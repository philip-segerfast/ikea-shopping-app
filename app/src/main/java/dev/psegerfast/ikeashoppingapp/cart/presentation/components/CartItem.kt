package dev.psegerfast.ikeashoppingapp.cart.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.app.ui.theme.DarkRed
import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.presentation.ListItemContainer
import dev.psegerfast.ikeashoppingapp.core.presentation.RoundedNumberPicker
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components.ProductDescription
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components.ProductPicture
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components.ProductPrice

@Composable
fun CartItem(
    cartItem: ProductBundle,
    onAddOne: () -> Unit,
    onRemoveOne: () -> Unit,
    onClearItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (product, quantity) = cartItem

    ListItemContainer(Modifier.fillMaxWidth().then(modifier)) {
        Column(Modifier.padding(8.dp)) {
            Row(
                Modifier
                    .height(IntrinsicSize.Min)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductPicture(product)
                Spacer(Modifier.width(8.dp))
                ProductDescription(product, modifier = Modifier.weight(1f))
                ProductPrice(product.price)
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = onClearItem,
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = stringResource(R.string.remove),
                        tint = DarkRed,
                    )
                }
                RoundedNumberPicker(
                    value = quantity,
                    onIncrement = onAddOne,
                    onDecrement = onRemoveOne,
                )
            }
        }
    }
}

@Preview
@Composable
fun CartItemPreview() {
    CartItem(
        cartItem = ProductBundle(
            product = Product.Couch(
                id = 0,
                name = "Lidhult",
                price = Money(959.0, "kr"),
                imageUrl = "",
                color = "white",
                seats = 3
            ),
            quantity = 1,
        ),
        onAddOne = {},
        onRemoveOne = {},
        onClearItem = {},
    )
}
