package org.zamahaka.cheremosh.ui.rv

interface BindViewHolder<in T> {
    fun bind(data: T)
}