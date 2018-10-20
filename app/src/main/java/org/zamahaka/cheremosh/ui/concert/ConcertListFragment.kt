package org.zamahaka.cheremosh.ui.concert

import android.arch.lifecycle.Observer
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_concert.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.ui.ReselectListener

class ConcertListFragment : Fragment(), ReselectListener {

    private val viewModel by viewModel<ConcertViewModel>()

    private val concertsAdapter = ConcertsAdapter {
        toast("Concert selected at position $it")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_concert, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(rvConcerts) {
            adapter = concertsAdapter

            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) = with(outRect) {
                    val spacing = dip(16)

                    left = spacing
                    right = spacing

                    bottom = spacing
                    top = spacing * if (parent.getChildAdapterPosition(view) == 0) 1 else 0
                }
            })
        }

        swipeToRefresh.setOnRefreshListener(viewModel::refresh)

        viewModel.concerts.observe(this, Observer { it?.let(concertsAdapter::submitList); swipeToRefresh.isRefreshing = false })
    }

    override fun onReselected() = rvConcerts.smoothScrollToPosition(0)
}