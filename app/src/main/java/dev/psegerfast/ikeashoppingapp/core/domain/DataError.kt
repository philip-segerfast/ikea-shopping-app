package dev.psegerfast.ikeashoppingapp.core.domain

sealed interface DataError: Error {

    sealed interface IO : DataError {
        data class Unknown(val error: Exception) : IO
        data class FileNotFound(val error: Exception) : IO
    }

    sealed interface Local : DataError {
        data object DiskFull : Local
        data class Unknown(val error: Exception) : Local
    }

}