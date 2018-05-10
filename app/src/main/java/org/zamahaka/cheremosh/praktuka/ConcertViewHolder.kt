package org.zamahaka.cheremosh.praktuka

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_concert.view.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.Url
import org.zamahaka.cheremosh.extensions.load
import org.zamahaka.cheremosh.praktuka.model.Concert
import java.text.SimpleDateFormat
import java.util.*

class ConcertViewHolder(
        view: View,
        onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {

    }

    fun bind(concert: Concert) = with(concert) {
        imageUrl.takeIf(Url::isNotEmpty)?.let { itemView.img.load(it) }
        itemView.txtName.text = name
        itemView.txtDescription.text = description
        itemView.txtTime.text = timeFormatter.format(time)
    }

    companion object {
        const val LAYOUT_RES = R.layout.item_concert

        private val timeFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    }

}
