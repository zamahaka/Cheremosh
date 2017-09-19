package org.zamahaka.cheremosh.extensions

import org.zamahaka.cheremosh.CallbackHolder
import retrofit2.Call

/**
 * Created by Yura Stetsyc on 19-9-17.
 */
inline fun <T> Call<T>.enqueue(init: CallbackHolder<T>.() -> Unit) {
    val holder = CallbackHolder<T>()
    holder.init()
    enqueue(holder)
}