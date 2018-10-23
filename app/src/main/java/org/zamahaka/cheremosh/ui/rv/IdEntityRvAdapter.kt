package org.zamahaka.cheremosh.ui.rv

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.zamahaka.cheremosh.model.IdEntity

abstract class IdEntityRvAdapter<T : IdEntity<*>, VH : RecyclerView.ViewHolder>(
        itemCallback: DiffUtil.ItemCallback<T> = IdEntityCallback()
) : ListAdapter<T, VH>(itemCallback) {

    @Suppress("UNCHECKED_CAST")
    protected fun <D : T> getItemAs(position: Int): D = getItem(position) as D


    class IdEntityCallback<T : IdEntity<*>>(
            private val getPayload: ((old: T, new: T) -> Any?)? = null
    ) : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

        override fun getChangePayload(oldItem: T, newItem: T) =
                getPayload?.invoke(oldItem, newItem)
    }

}