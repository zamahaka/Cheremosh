package org.zamahaka.cheremosh.ui.timeline.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event.view.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.model.Event

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mName = view.txtName
    private val mTime = view.txtTime

    fun bind(event: Event) {
        mName.text = event.name
        mTime.text = event.date.toString()
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.item_event
    }

}