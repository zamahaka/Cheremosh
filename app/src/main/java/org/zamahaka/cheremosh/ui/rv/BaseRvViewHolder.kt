package org.zamahaka.cheremosh.ui.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseRvViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer