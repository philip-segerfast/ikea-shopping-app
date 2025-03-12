package dev.psegerfast.ikeashoppingapp.cart.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaYellow

@Composable
fun ProceedToCheckoutButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Button(
        onClick = { Toast.makeText(context, R.string.to_be_continued, Toast.LENGTH_LONG).show() }
    ) {
        Row {
            Icon(
                Icons.Outlined.ShoppingCart,
                contentDescription = null,
                tint = IkeaYellow
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.proceed_too_checkout).capitalize(Locale.current),
                color = IkeaYellow,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}