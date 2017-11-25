package org.zamahaka.cheremosh.ui.timeline.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.toast
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

    init {
        RxView.clicks(itemView)
                .subscribe { itemView.context.toast("${mName.text} clicked") }
    }

    fun bind(event: Event) {
        mName.text = event.name
        mTime.text = DateFormats.ItemEventTime.format(Date(event.date))
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.item_event
    }

}