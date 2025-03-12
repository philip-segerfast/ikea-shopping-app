package dev.psegerfast.ikeashoppingapp.core.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundedNumberPicker(
    value: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(100)
    val density = LocalDensity.current
    Surface(
        modifier = modifier,
        shape = shape,
        shadowElevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onDecrement) {
                Text("-")
            }
            Text(
                text = value.toString(),
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onIncrement) {
                Text("+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedNumberPickerPreview(modifier: Modifier = Modifier) {
    RoundedNumberPicker(
        value = 0,
        onIncrement = {},
        onDecrement = {},
    )
}