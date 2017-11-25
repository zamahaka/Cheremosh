package org.zamahaka.cheremosh.ui.timeline

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.io.TimeLineService
import org.zamahaka.cheremosh.model.Event
import org.zamahaka.cheremosh.redux.Action

/**
 * Created by zamahaka on 28.10.2017.
 */
class TimeLineMiddleware(private val timeLineService: TimeLineService) {
    fun getTimeLine(timestamp: Timestamp): Observable<TimeLineAction> =
            timeLineService.loadTimeLine(timestamp) //TODO convert to 0h:0m:0s
                    .map { TimeLineAction.TimeLineSuccess(it.sortedBy { item -> item.date }) as TimeLineAction }
                    .subscribeOn(Schedulers.io())
                    .onErrorReturn { TimeLineAction.TimeLineError(it) }

}

sealed class TimeLineAction : Action {
    data class TimeLineSuccess(val events: List<Event>) : TimeLineAction()
    data class TimeLineError(val error: Throwable) : TimeLineAction()
    object TimeLineProgress : TimeLineAction()
}