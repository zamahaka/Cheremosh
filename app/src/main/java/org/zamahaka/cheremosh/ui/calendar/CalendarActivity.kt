package org.zamahaka.cheremosh.ui.calendar

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_callendar.*
import kotlinx.android.synthetic.main.item_day.view.*
import kotlinx.android.synthetic.main.item_task.view.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.ui.drawable.BottomAngleDrawable

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callendar)

        calendarRv.apply {
            layoutManager = GridLayoutManager(this@CalendarActivity, 7)
            adapter = object : RecyclerView.Adapter<DayViewHolder>() {
                val items: List<Int> = mutableListOf<Int>().apply { repeat(30) { add(it + 1) } }

                override fun getItemCount() = items.size

                override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
                    holder.dayTxt.text = items[position].toString()
                }

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DayViewHolder(
                        LayoutInflater.from(parent.context)
                                .inflate(DayViewHolder.LAYOUT_ID, parent, false)
                )
            }

            Picasso.with(this@CalendarActivity).isLoggingEnabled = true
            val target: Target = object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

                override fun onBitmapFailed(errorDrawable: Drawable?) {
                    tag = null
                }

                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
                    tag = null
                    background = BottomAngleDrawable(BitmapDrawable(bitmap), 5)
                }

            }
            tag = target
            Picasso.with(this@CalendarActivity).load("file:///android_asset/test/img/bg.png")
                    .into(target)
        }

        tasksRv.apply {
            layoutManager = LinearLayoutManager(this@CalendarActivity)
            adapter = object : RecyclerView.Adapter<TaskViewHolder>() {
                val items = listOf(Task(
                        10, "am", "file:///android_asset/test/avatars/bc.png", "Coffee with Adam", "Starbucks", null
                ), Task(
                        11, "am", "file:///android_asset/test/avatars/rdj.png", "Catch up with Marie", "Hangouts", null
                ), Task(
                        2, "pm", "file:///android_asset/test/avatars/cg.png", "Lunch with Diane", "Macdonald's", null
                ))

                override fun getItemCount() = items.size

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder(
                        LayoutInflater.from(parent.context)
                                .inflate(TaskViewHolder.LAYOUT_ID, parent, false)
                )

                override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.run {
                    val (hour, period, avatar, title, subTitle, status) = items[position]

                    hourTxt.text = hour.toString()
                    periodTxt.text = period
                    Picasso.with(itemView.context).load(avatar).into(avatarImg)
                    titleTxt.text = title
                    subTitleTxt.text = subTitle
                }
            }
            addItemDecoration(
                    DividerItemDecoration(this@CalendarActivity, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ColorDrawable(Color.LTGRAY))
                    }
            )
        }

    }

}

class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dayTxt: TextView = view.txtDay

    companion object {
        @LayoutRes val LAYOUT_ID = R.layout.item_day
    }
}

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val hourTxt: TextView = view.txtHour
    val periodTxt: TextView = view.txtPeriod
    val avatarImg: ImageView = view.imgAvatar
    val titleTxt: TextView = view.txtTitle
    val subTitleTxt: TextView = view.txtSubTitle
    val statusImg: ImageView = view.imgStatus

    companion object {
        @LayoutRes val LAYOUT_ID = R.layout.item_task
    }
}

data class Task(
        val hour: Int,
        val period: String,
        val avatar: String,
        val title: String,
        val subTitle: String,
        val status: String?
)