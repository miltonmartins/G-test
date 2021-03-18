package com.martins.milton.goktest.utils.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> CoroutineScope.customLaunch(
    blockToRun: suspend CoroutineScope.() -> T,
    onLoading: ((Boolean) -> Unit)? = null,
    onError: ((Exception) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null
) {
    launch {
        withContext(Dispatchers.Main) {
            onLoading?.invoke(true)
        }
        try {
            val result = withContext(Dispatchers.IO) {
                return@withContext blockToRun()
            }
            withContext(Dispatchers.Main) {
                onLoading?.invoke(false)
                onSuccess?.invoke(result)
            }
        } catch (exception: Exception) {
            withContext(Dispatchers.Main) {
                onLoading?.invoke(false)
                onError?.invoke(exception)
            }
        }
    }
}

fun <T, R> CoroutineScope.launchZip(
    blockToRun: suspend CoroutineScope.() -> Pair<T, R>,
    onLoading: ((Boolean) -> Unit)? = null,
    onError: ((Exception) -> Unit)? = null,
    onSuccess: ((Pair<T, R>) -> Unit)? = null
) {
    launch {
        withContext(Dispatchers.Main) {
            onLoading?.invoke(true)
        }
        try {
            val result = withContext(Dispatchers.IO) {
                return@withContext blockToRun()
            }
            withContext(Dispatchers.Main) {
                onLoading?.invoke(false)
                onSuccess?.invoke(result)
            }
        } catch (exception: Exception) {
            withContext(Dispatchers.Main) {
                onLoading?.invoke(false)
                onError?.invoke(exception)
            }
        }
    }
}

