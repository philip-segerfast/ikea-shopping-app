package dev.psegerfast.ikeashoppingapp.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaBlue
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaYellow
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.presentation.uiString

@Composable
fun BottomSheetDragHandle(
    shape: CornerBasedShape,
    totalPrice: Money,
    itemCount: Int,
    onHeightSet: (Dp) -> Unit,
    onClearCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val finalShape = shape.copy(bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp))
    val localDensity = LocalDensity.current
    val minimumSize = LocalMinimumInteractiveComponentSize.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .testTag("BottomSheetDragHandle")
            .onGloballyPositioned { layoutCoordinates ->
                localDensity.run {
                    onHeightSet(layoutCoordinates.size.height.toDp())
                }
            },
        shape = finalShape,
    ) {
        Row(
            Modifier
                .height(minimumSize)
                .background(IkeaBlue, finalShape)
                .padding(horizontal = 16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val strTotal = stringResource(R.string.total).capitalize(Locale.current)
            val strItems = pluralStringResource(R.plurals.items, itemCount)
            Text(
                text = "$strTotal: ${totalPrice.uiString()} ($itemCount $strItems)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = IkeaYellow,
            )
            Spacer(Modifier.weight(1f))
            TextButton(
                onClick = onClearCart
            ) {
                Text(
                    stringResource(R.string.clear_cart).capitalize(Locale.current),
                    textDecoration = TextDecoration.Underline,
                    color = IkeaYellow,
                )
            }
        }
    }
}