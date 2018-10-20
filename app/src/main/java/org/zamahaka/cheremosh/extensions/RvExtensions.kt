package org.zamahaka.cheremosh.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun RecyclerView.Adapter<*>.inflate(@LayoutRes id: Int, parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(id, parent, false)

val RecyclerView.ViewHolder.context: Context
    get() = itemView?.context
            ?: throw IllegalStateException("Accessing context of not yet initialized viewHolder: $this")

@ColorInt
fun RecyclerView.ViewHolder.getColor(@ColorRes colorId: Int) = context.getColorCompat(colorId)

fun RecyclerView.ViewHolder.getString(@StringRes stringId: Int, vararg args: Any): String = context.getString(stringId, *args)

fun RecyclerView.ViewHolder.getDrawable(@DrawableRes drawableId: Int): Drawable = context.getDrawableCompat(drawableId)

fun RecyclerView.ViewHolder.onClick(view: View, handler: (adapterPosition: Int) -> Unit) =
        view.onClick { adapterPosition.takeIf { it != -1 }?.let(handler) }