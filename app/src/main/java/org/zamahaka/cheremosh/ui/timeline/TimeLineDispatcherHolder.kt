package org.zamahaka.cheremosh.ui.timeline

import io.reactivex.android.schedulers.AndroidSchedulers
import org.zamahaka.cheremosh.io.FirebaseTimeLineService
import org.zamahaka.cheremosh.redux.DispatcherHolder
import org.zamahaka.cheremosh.redux.SimpleStore

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineDispatcherHolder : DispatcherHolder<TimeLineState, TimeLineAction, TimeLineViewState, TimeLineDispatcher>(
        TimeLineDispatcher(
                TimeLineMiddleware(FirebaseTimeLineService())::getTimeLine,
                SimpleStore(emptyTimeLineState, TimeLineReducer),
                AndroidSchedulers.mainThread()
        )
)