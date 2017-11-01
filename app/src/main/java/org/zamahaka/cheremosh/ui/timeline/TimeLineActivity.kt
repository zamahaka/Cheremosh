package org.zamahaka.cheremosh.ui.timeline

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_time_line.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.redux.DispatcherBinder
import org.zamahaka.cheremosh.redux.getDispatcher
import org.zamahaka.cheremosh.ui.rv.ItemTinter
import org.zamahaka.cheremosh.ui.timeline.adapter.TimeLineAdapter
import java.util.*

class TimeLineActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val timeLineAdapter = TimeLineAdapter()
    private val disposables = CompositeDisposable()

    private val timeLineDispatcher by lazy { getDispatcher(TimeLineDispatcherHolder::class) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        recyclerView.adapter = timeLineAdapter
        recyclerView.addItemDecoration(ItemTinter(ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))

        DispatcherBinder(this.lifecycle, timeLineDispatcher) { (events) ->
            timeLineAdapter.bindResults(events ?: emptyList())
//            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//            errorMessage.text = error?.message
        }

        datePicker.setOnDateSelectedListener { year, month, day, _ ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            timeLineDispatcher.loadTimeLine(calendar.timeInMillis)
        }
    }

    override fun onStart() {
        super.onStart()
        disposables.add(timeLineDispatcher.loadTimeLine(RxView.clicks(btnRetry)))
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun getLifecycle() = lifecycleRegistry

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, TimeLineActivity::class.java))
        }
    }
}
