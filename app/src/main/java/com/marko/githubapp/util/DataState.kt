package com.marko.githubapp.util

/**
 * Reflects the state of the data being processed
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val message: String = "") : DataState<Nothing>()

    object Loading : DataState<Nothing>()
}
