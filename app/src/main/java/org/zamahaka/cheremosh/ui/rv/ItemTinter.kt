package org.zamahaka.cheremosh.ui.rv

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by zamahaka on 30.10.2017.
 */
class ItemTinter(@ColorInt color: Int) : RecyclerView.ItemDecoration() {

    private val bg = ColorDrawable(color)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount

        if (childCount == 0) return

        for (i in 0..childCount step 2) {
            parent.getChildAt(i)?.let {
                bg.setBounds(it.left, it.top, it.right, it.bottom)
                bg.draw(c)
            }
        }
    }
}