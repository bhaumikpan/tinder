package com.example.network_data.extensions

/**
 * ResultExtension provides extension of kotlin Result class
 * if needed we can more centralized logic to handle network / any communication error handling
 * if needed we can use Default for initialization of Objects
 *
 * returning Result object as CoreResult
 */

sealed class CoreResult<out T> {
    data class OnSuccess<out T>(val data: T) : CoreResult<T>()
    data class OnError(val error: Throwable?) : CoreResult<Nothing>()
    data object Default : CoreResult<Nothing>()

    val isSuccess: Boolean
        get() = this is OnSuccess<*>
}
