package org.zamahaka.cheremosh.redux

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KClass

/**
 * Created by Yura Stetsyc on 12-9-17.
 */

fun <S : State, A : Action, VS : ViewState, T : ReduxDispatcher<S, A, VS>> Fragment.getDispatcher(
        clazz: KClass<out DispatcherHolder<S, A, VS, T>>): T {
    return ViewModelProviders.of(this).get(clazz.java).dispatcher
}

fun <S : State, A : Action, VS : ViewState, T : ReduxDispatcher<S, A, VS>> FragmentActivity.getDispatcher(
        clazz: KClass<out DispatcherHolder<S, A, VS, T>>): T {
    return ViewModelProviders.of(this).get(clazz.java).dispatcher
}