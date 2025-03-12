package dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.product.domain.Product

@Composable
fun ProductDescription(
    product: Product,
    modifier: Modifier = Modifier,
) {
    val locale = Locale.current
    val chairStringResource = stringResource(R.string.chair)
    val couchStringResource = stringResource(R.string.couch)

    val productDescription = when(product) {
        is Product.Chair -> {
            "$chairStringResource, ${product.material}"
        }
        is Product.Couch -> {
            val pluralSeats = pluralStringResource(R.plurals.seats, product.seats)
            "$couchStringResource, ${product.seats} $pluralSeats"
        }
    }

    Column(modifier.fillMaxHeight()) {
        Text(
            text = product.name.uppercase(),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = productDescription.capitalize(locale),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "${ stringResource(R.string.color).capitalize(locale) }: ${ product.color }",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}