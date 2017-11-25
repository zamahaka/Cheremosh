package org.zamahaka.cheremosh.redux

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Yura Stetsyc on 12-9-17.
 */
abstract class ReduxDispatcher<S : State, in A : Action, out VS : ViewState>(
        protected val store: Store<S, A>,
        private val mainScheduler: Scheduler,
        private val viewStateMapper: (S) -> VS
) {

    /**
     * Override to transform the state
     */
    protected open val state: Observable<S> = store.asObservable()
    private val subscriptions = CompositeDisposable()

    protected fun addSubscription(subscription: Disposable) = subscriptions.add(subscription)

    fun subscribe(onNext: (viewState: VS) -> Unit): Disposable = state
            .map(viewStateMapper)
            .observeOn(mainScheduler)
            .subscribe(onNext)

    fun onCleared() {
        store.tearDown()
        subscriptions.dispose()
    }

}