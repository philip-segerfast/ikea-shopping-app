package dev.psegerfast.ikeashoppingapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import okhttp3.internal.format
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

@Composable
fun Money.uiString(): String {
    val locale = Locale.current

    val rounded: String = String.format(
        locale = locale.platformLocale,
        format = if (value % 1.0 == 0.0) "%.0f" else "%.2f",
        value
    )

    return "$rounded $currency"
}