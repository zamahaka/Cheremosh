package org.zamahaka.cheremosh.ui.timeline

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.redux.ReduxDispatcher
import org.zamahaka.cheremosh.redux.Store

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineDispatcher(
        private val timeLineMiddleware: (Timestamp) -> Observable<TimeLineAction>,
        store: Store<TimeLineState, TimeLineAction>,
        scheduler: Scheduler)
    : ReduxDispatcher<TimeLineState, TimeLineAction, TimeLineViewState>(
        store,
        scheduler,
        ::mapTimeLineStateToTimeLineViewState
) {

    init {
        store.dispatch(timeLineMiddleware(System.currentTimeMillis()))
    }

    fun loadTimeLine(clicks: Observable<Any>): Disposable = clicks
            .filter { !store.currentState().isLoading }
            .subscribe { store.dispatch(timeLineMiddleware(System.currentTimeMillis())) }

    fun loadTimeLine(timestamp: Timestamp) = /*clicks*/
//            .filter { !store.currentState().isLoading }
//            .subscribe { store.dispatch(timeLineMiddleware(timestamp)) }
            store.dispatch(timeLineMiddleware(timestamp))

}