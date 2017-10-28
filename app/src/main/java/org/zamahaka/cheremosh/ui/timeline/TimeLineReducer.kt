package org.zamahaka.cheremosh.ui.timeline

import org.zamahaka.cheremosh.redux.Reducer

/**
 * Created by zamahaka on 28.10.2017.
 */
object TimeLineReducer : Reducer<TimeLineState, TimeLineAction> {
    override fun reduce(oldState: TimeLineState, action: TimeLineAction) = when (action) {
        is TimeLineAction.TimeLineError -> emptyTimeLineState.copy(error = action.error)
        is TimeLineAction.TimeLineSuccess -> emptyTimeLineState.copy(events = action.events)
        is TimeLineAction.TimeLineProgress -> emptyTimeLineState.copy(isLoading = true)
    }
}