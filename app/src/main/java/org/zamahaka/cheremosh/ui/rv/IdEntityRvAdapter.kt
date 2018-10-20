package org.zamahaka.cheremosh.ui.rv

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import org.zamahaka.cheremosh.model.IdEntity

abstract class IdEntityRvAdapter<T : IdEntity<*>, VH : RecyclerView.ViewHolder>(
        itemCallback: DiffUtil.ItemCallback<T> = DefaultIdEntityCallback()
) : ListAdapter<T, VH>(itemCallback) {

    @Suppress("UNCHECKED_CAST")
    protected fun <D : T> getItemAs(position: Int): D = getItem(position) as D

    companion object {
        @Suppress("FunctionName")
        fun <T : IdEntity<*>> DefaultIdEntityCallback() = object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
        }
    }

}