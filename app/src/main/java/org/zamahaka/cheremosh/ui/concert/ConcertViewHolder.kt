package org.zamahaka.cheremosh.ui.concert

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_concert.view.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.Url
import org.zamahaka.cheremosh.domain.model.Concert
import org.zamahaka.cheremosh.extensions.load
import java.text.SimpleDateFormat
import java.util.*

class ConcertViewHolder(
        view: View,
        onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener { adapterPosition.takeIf { it != -1 }?.let(onClick) }
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
