package org.zamahaka.cheremosh.extensions

import androidx.lifecycle.*

/**
 * Created by Yura Stetsyc on 06-9-17.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit) =
        observe(lifecycleOwner, Observer(onChanged))

fun <T : Any> LiveData<T>.asMutable(): MutableLiveData<T> = MediatorLiveData<T>().apply {
    addSource(this@asMutable) { value = it }
}

fun <T : Any, R : Any> LiveData<T>.map(mutator: (T) -> R): LiveData<R> = Transformations
        .map(this, mutator)