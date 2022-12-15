package com.gbilgeturk.pokemonapp.service

sealed class AppResult<out T> {
    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(
        private val exception: java.lang.Exception? = null,
        val message: String = exception?.localizedMessage ?: "",
        val type: ErrorType = ErrorType.Default
    ) : AppResult<Nothing>()
}
enum class ErrorType {
    Default
}