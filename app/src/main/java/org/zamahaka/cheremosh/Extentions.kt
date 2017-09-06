package org.zamahaka.cheremosh

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zamahaka.wotsapp.mvp.ActiveView
import com.zamahaka.wotsapp.mvp.BasePresenter
import retrofit2.Call

/**
 * Created by Yura Stetsyc on 06-9-17.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit)
        = observe(lifecycleOwner, Observer(onChanged))

inline fun <T> Call<T>.enqueue(init: CallbackHolder<T>.() -> Unit) {
    val holder = CallbackHolder<T>()
    holder.init()
    enqueue(holder)
}

fun ImageView.load(url: Url) = Picasso.with(context).load(url).fit().centerInside().into(this)

val ActiveView?.isValid: Boolean get() = this?.isActive ?: false

inline fun <V : ActiveView> BasePresenter<V>.performViewOperation(operation: V.() -> Unit) {
    if (view.isValid) view?.operation()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}