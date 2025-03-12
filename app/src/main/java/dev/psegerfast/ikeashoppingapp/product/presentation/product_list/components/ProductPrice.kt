package dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.presentation.uiString

@Composable
fun ProductPrice(
    price: Money,
    modifier: Modifier = Modifier
) {
    Text(
        text = price.uiString(),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
    )
}