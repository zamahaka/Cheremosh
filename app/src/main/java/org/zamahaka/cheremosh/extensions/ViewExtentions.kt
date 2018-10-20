@file:Suppress("UsePropertyAccessSyntax")

package org.zamahaka.cheremosh.extensions

import android.view.View

val View.isVisible: Boolean get() = visibility == View.VISIBLE
fun View.visible() = setVisibility(View.VISIBLE)

val View.isInvisible: Boolean get() = visibility == View.INVISIBLE
fun View.invisible() = setVisibility(View.INVISIBLE)

val View.isGone: Boolean get() = visibility == View.GONE
fun View.gone() = setVisibility(View.GONE)

inline fun View.onClick(crossinline op: () -> Unit) = setOnClickListener { op() }
fun View.clearOnClick() = setOnClickListener(null)

fun View.hideKeyboard() = context.hideKeyboard(this)
fun View.showKeyboard() = context.showKeyboard(this)

val View.isNotVisibleWithParents: Boolean
    get() {
        if (!isVisible) return true

        return (parent as? View)?.isNotVisibleWithParents ?: false
    }