package org.zamahaka.cheremosh

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yura Stetsyc on 06-9-17.
 */
class CallbackHolder<T> : Callback<T> {

    private var onResponse: ((response: Response<T>) -> Unit)? = null
    private var onFailure: ((t: Throwable) -> Unit)? = null
    private var onComplete: (() -> Unit)? = null

    fun onResponse(onResponse: (Response<T>) -> Unit) {
        this.onResponse = onResponse
    }

    fun onFailure(onFailure: (Throwable) -> Unit) {
        this.onFailure = onFailure
    }

    fun onComplete(onComplete: () -> Unit) {
        this.onComplete = onComplete
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onComplete?.invoke()
        onResponse?.invoke(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onComplete?.invoke()
        onFailure?.invoke(t)
    }
}