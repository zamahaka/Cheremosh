package org.zamahaka.cheremosh.ui.rv

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface ViewHolderViewCreator {
    val layoutRes: Int
        @LayoutRes get

    fun inflate(parent: ViewGroup): View =
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
}