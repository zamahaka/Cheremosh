package org.zamahaka.cheremosh.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.toast

@ColorInt
fun Context.getColorCompat(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable = ContextCompat.getDrawable(this, resId)
        ?: throw IllegalAccessException("Cant find drawable for id: ${resId.toString(16)}")

fun Context.notImplementedToast() = toast("This action is not implemented yet. \uD83D\uDE05")


fun Context.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            view, InputMethodManager.SHOW_IMPLICIT
    )
}