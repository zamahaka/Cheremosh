package org.zamahaka.cheremosh.ui.timeline.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event.view.*
import org.zamahaka.cheremosh.DateFormats
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.model.Event
import java.util.*

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mName = itemView.txtName
    private val mTime = itemView.txtTime

    fun bind(event: Event) {
        mName.text = event.name
        mTime.text = DateFormats.ItemEventTime.format(Date(event.date))
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.item_event
    }

}