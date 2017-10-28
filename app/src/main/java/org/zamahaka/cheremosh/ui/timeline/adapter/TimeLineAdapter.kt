package org.zamahaka.cheremosh.ui.timeline.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.zamahaka.cheremosh.model.Event

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineAdapter : RecyclerView.Adapter<TimeLineViewHolder>() {

    private var events = mutableListOf<Event>()

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) = holder.bind(events[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TimeLineViewHolder(
            LayoutInflater.from(parent.context).inflate(TimeLineViewHolder.LAYOUT, parent, false))

    fun bindResults(events: List<Event>) {
        this.events.clear()
        this.events.addAll(events)
        notifyDataSetChanged()
    }

}