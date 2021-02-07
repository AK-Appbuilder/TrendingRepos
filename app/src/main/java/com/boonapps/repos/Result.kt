package com.boonapps.repos

import androidx.lifecycle.MutableLiveData

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    object Empty : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Empty -> "Empty"
            Loading -> "Loading"
        }
    }
}


suspend fun <T> callApi(apiCall: suspend () -> T): Result<T> {
    return try {
        val result = apiCall.invoke()
        if (result.isEmptyResponse()) {
            Result.Empty
        } else {
            Result.Success(result)
        }
    } catch (throwable: Throwable) {
        error(throwable)
    }
}


val Result<*>.succeeded
    get() = this is Result.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

fun <T> T.isEmptyResponse(): Boolean {
    return this != null && this is List<*> && this.isEmpty()
}

inline fun <reified T> Result<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is Result.Success) {
        liveData.value = data
    }
}