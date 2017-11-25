package org.zamahaka.cheremosh.extensions

import android.view.View

/**
 * Created by Yura Stetsyc on 19-9-17.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}