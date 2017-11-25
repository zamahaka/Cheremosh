package org.zamahaka.cheremosh.ui.timeline

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.b3er.rxfirebase.database.data
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_time_line.*
import org.jetbrains.anko.toast
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.model.Event
import org.zamahaka.cheremosh.model.Location
import org.zamahaka.cheremosh.redux.DispatcherBinder
import org.zamahaka.cheremosh.redux.getDispatcher
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
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildAdapterPosition(view)
                outRect.set(40, 40, 40, if (position == parent.adapter.itemCount - 1) 40 else 0)
            }
        })

        DispatcherBinder(this.lifecycle, timeLineDispatcher) { (events) ->
            timeLineAdapter.bindResults(events ?: emptyList())
//            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//            errorMessage.text = error?.message

            recyclerView.invalidateItemDecorations()
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
//        disposables.add(timeLineDispatcher.loadTimeLine(RxView.clicks(fabAdd)))
        RxView.clicks(fabAdd).subscribe {
            FirebaseDatabase.getInstance().reference
                    .child("events")
                    .child(/*timestamp*/1509140624L.toString())
                    .ref
                    .data()
                    .doOnSuccess {
                        val list = it.value as List<*>
                        it.ref
                                .child(list.size.toString())
                                .setValue(Event(
                                        date = System.currentTimeMillis(),
                                        visitors = listOf("testUidBass"),
                                        name = "Generated event at time ${System.currentTimeMillis()}",
                                        location = Location(latitude = 49.83839, longitude = 24.023323),
                                        description = "Generated event description",
                                        type = "repa"
                                ))
                    }
                    .doOnError { toast(it.message.toString()) }
                    .subscribe()

        }
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
