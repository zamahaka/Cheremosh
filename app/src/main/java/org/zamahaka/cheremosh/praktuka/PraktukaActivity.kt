package org.zamahaka.cheremosh.praktuka

import android.arch.lifecycle.Observer
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_praktuka.*
import org.jetbrains.anko.dip
import org.koin.android.architecture.ext.viewModel
import org.zamahaka.cheremosh.R

class PraktukaActivity : AppCompatActivity() {

    private val viewModel by viewModel<ConcertViewModel>()

    private lateinit var adapter: ConcertsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_praktuka)

        with(rvConcerts) {
            adapter = ConcertsAdapter {}.also { this@PraktukaActivity.adapter = it }

            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) = with(outRect) {
                    val spacing = dip(16)

                    left = spacing
                    right = spacing

                    top = spacing * if (parent.getChildAdapterPosition(view) == 0) 1 else 0
                    bottom = spacing * if (parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1) 1 else 1
                }
            })
        }

        swipeToRefresh.setOnRefreshListener(viewModel::refresh)

        viewModel.concerts.observe(this, Observer { it?.let(adapter::submitList); swipeToRefresh.isRefreshing = false })
    }

}
