package org.zamahaka.cheremosh.ui.timeline

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_time_line.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.ui.timeline.adapter.TimeLineAdapter

class TimeLineActivity : AppCompatActivity() {

    private val timeLineAdapter = TimeLineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        recyclerView.adapter = timeLineAdapter
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildAdapterPosition(view)
                outRect.set(40, 40, 40, if (position == parent.adapter.itemCount - 1) 40 else 0)
            }
        })
    }

}
