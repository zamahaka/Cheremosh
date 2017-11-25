package org.zamahaka.cheremosh.ui.timeline

/**
 * Created by zamahaka on 28.10.2017.
 */

fun mapTimeLineStateToTimeLineViewState(timeLineState: TimeLineState) : TimeLineViewState =
        TimeLineViewState(timeLineState.events, timeLineState.error, timeLineState.isLoading)