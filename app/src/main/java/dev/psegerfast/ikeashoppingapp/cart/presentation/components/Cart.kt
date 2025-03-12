package dev.psegerfast.ikeashoppingapp.cart.presentation.components

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaBlue
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaYellow
import dev.psegerfast.ikeashoppingapp.cart.domain.ProductBundle
import dev.psegerfast.ikeashoppingapp.cart.presentation.CartAction
import dev.psegerfast.ikeashoppingapp.cart.presentation.CartUiState
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.presentation.uiString
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.presentation.type

@Composable
fun Cart(
    state: CartUiState,
    onAction: (CartAction) -> Unit,
) {
    val cartItems = state.products

    val itemSpacing = 16.dp
    Surface {
        Column(modifier = Modifier.background(IkeaBlue)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(itemSpacing),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(
                    items = cartItems,
                    key = { it.product.id },
                    contentType = { it.product.type() },
                ) { cartItem ->
                    val product = cartItem.product
                    CartItem(
                        modifier = Modifier.animateItem(),
                        cartItem = cartItem,
                        onRemoveOne = { onAction(CartAction.RemoveSingleFromCart(product)) },
                        onAddOne = { onAction(CartAction.AddToCart(product)) },
                        onClearItem = { onAction(CartAction.ClearFromCart(product)) }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                ProceedToCheckoutButton()
            }
        }
    }
}

@Preview
@Composable
private fun CartPreview() {
    val cartUiState = CartUiState(
        products = mockItems,
        valueTotal = Money(1258.0, "kr")
    )

    Cart(
        state = cartUiState,
        onAction = {}
    )
}

private val mockItems = listOf(
    ProductBundle(
        product = Product.Couch(
            id = 0,
            name = "Hänga",
            price = Money(959.0, "kr"),
            imageUrl = "abc",
            color = "White",
            seats = 3
        ),
        quantity = 2
    ),
    ProductBundle(
        product = Product.Chair(
            id = 1,
            name = "Sitta",
            price = Money(299.0, "kr"),
            imageUrl = "abc",
            color = "Brown",
            material = "Leather"
        ),
        quantity = 2
    )
)