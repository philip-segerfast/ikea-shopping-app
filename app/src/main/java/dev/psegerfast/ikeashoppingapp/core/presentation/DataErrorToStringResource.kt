package dev.psegerfast.ikeashoppingapp.core.presentation

import androidx.annotation.StringRes
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.core.domain.DataError

@StringRes
fun DataError.stringResource(): Int = when(this) {
    is DataError.IO.FileNotFound -> R.string.error_products_json_not_found
    is DataError.IO.Unknown -> R.string.error_unknown
    DataError.Local.DiskFull -> R.string.error_disk_full
    is DataError.Local.Unknown -> R.string.error_unknown
}