package org.zamahaka.cheremosh.redux

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 * Created by Yura Stetsyc on 12-9-17.
 */
class SimpleStore<S : State, in A : Action>(
        private val initialState: S,
        reducer: Reducer<S, A>
) : Store<S, A> {

    private val actionsSubject = PublishSubject.create<A>()
    private val statesSubject = BehaviorSubject.create<S>()

    private val compositeDis = CompositeDisposable().apply {
        actionsSubject
                .scan(initialState, reducer::reduce)
                .distinctUntilChanged()
                .subscribe { statesSubject.onNext(it) }
    }

    override fun dispatch(action: A) = actionsSubject.onNext(action)

    override fun dispatch(actions: Observable<out A>) {
        compositeDis.add(actions.subscribe { dispatch(it) })
    }

    override fun asObservable(): Observable<S> = statesSubject

    override fun currentState(): S = if (statesSubject.hasValue()) statesSubject.value else initialState

    override fun tearDown() = compositeDis.dispose()
}