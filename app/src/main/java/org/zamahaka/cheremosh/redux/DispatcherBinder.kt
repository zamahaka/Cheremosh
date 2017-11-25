package org.zamahaka.cheremosh.redux

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

/**
 * Created by zamahaka on 28.10.2017.
 */
class DispatcherBinder<S : State, A : Action, VS : ViewState>(lifecycle: Lifecycle,
                                                              private val reduxDispatcher: ReduxDispatcher<S, A, VS>,
                                                              private val onNext: (VS) -> Unit
) : LifecycleObserver {

    private var disposable: Disposable? = null

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        disposable = reduxDispatcher.subscribe(onNext)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }
}
