package org.zamahaka.cheremosh.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by Yura Stetsyc on 06-9-17.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit)
        = observe(lifecycleOwner, Observer(onChanged))