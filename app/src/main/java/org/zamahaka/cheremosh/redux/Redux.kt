package org.zamahaka.cheremosh.redux

import io.reactivex.Observable


/**
 * Created by Yura Stetsyc on 12-9-17.
 */

interface State

interface Action

interface Reducer<S : State, in A : Action> {
    fun reduce(oldState: S, action: A): S
}

interface Store<S : State, in A : Action> {
    fun dispatch(action: A)
    fun dispatch(actions: Observable<out A>)
    fun asObservable():Observable<S>
    fun currentState() : S
    fun tearDown()
}

