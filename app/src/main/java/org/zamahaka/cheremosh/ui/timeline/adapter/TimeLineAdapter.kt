package org.zamahaka.cheremosh.ui.timeline.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.zamahaka.cheremosh.model.Event

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineAdapter : RecyclerView.Adapter<TimeLineViewHolder>() {

    private var events = emptyList<Event>()

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) = holder.bind(events[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TimeLineViewHolder(
            LayoutInflater.from(parent.context).inflate(TimeLineViewHolder.LAYOUT, parent, false))

    fun bindResults(newEvents: List<Event>) {
        val oldEvents = events
        events = newEvents

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldEvents[oldItemPosition]
                val newItem = newEvents[newItemPosition]

                return oldItem.date == newItem.date
            }

            override fun getOldListSize() = oldEvents.size

            override fun getNewListSize() = newEvents.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldEvents[oldItemPosition]
                val newItem = newEvents[newItemPosition]

                return oldItem == newItem
            }

        }).dispatchUpdatesTo(this)
    }


}