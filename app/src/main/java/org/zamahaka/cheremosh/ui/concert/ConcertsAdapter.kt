package org.zamahaka.cheremosh.ui.concert

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import org.zamahaka.cheremosh.domain.model.Concert

class ConcertsAdapter(
        private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Concert, ConcertViewHolder>(ConcertDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(ConcertViewHolder.LAYOUT_RES, parent, false)
        return ConcertViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ConcertViewHolder, position: Int) = holder.bind(getItem(position))

    companion object {
        @Suppress("FunctionName")
        fun ConcertDiffCallback() = object : DiffUtil.ItemCallback<Concert>() {
            override fun areItemsTheSame(oldItem: Concert, newItem: Concert) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Concert, newItem: Concert) = oldItem == newItem
        }
    }

}
