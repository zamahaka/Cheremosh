package org.zamahaka.cheremosh.redux

import android.arch.lifecycle.ViewModel

/**
 * Created by Yura Stetsyc on 12-9-17.
 */
abstract class DispatcherHolder<S : State, in A : Action, out VS : ViewState, out D : ReduxDispatcher<S, A, VS>>(
        val dispatcher: D
) : ViewModel() {

    override fun onCleared() = dispatcher.onCleared()

}