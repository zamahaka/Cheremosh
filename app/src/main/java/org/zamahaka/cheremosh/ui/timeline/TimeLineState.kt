package org.zamahaka.cheremosh.ui.timeline

import org.zamahaka.cheremosh.model.Event
import org.zamahaka.cheremosh.redux.State

/**
 * Created by zamahaka on 28.10.2017.
 */
data class TimeLineState(val events: List<Event>?, val error: Throwable?, val isLoading: Boolean) : State

val emptyTimeLineState = TimeLineState(null, null, false)