package dev.psegerfast.ikeashoppingapp.core.domain

import android.database.sqlite.SQLiteOutOfMemoryException

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

suspend inline fun <T> runCatchingDatabaseErrors(block: suspend () -> T): Result<T, DataError.Local> {
    return try {
        val result = block()
        Result.Success(result)
    } catch (e: SQLiteOutOfMemoryException) {
        Result.Error(DataError.Local.DiskFull)
    } catch (e: Exception) {
        Result.Error(DataError.Local.Unknown(e))
    }
}