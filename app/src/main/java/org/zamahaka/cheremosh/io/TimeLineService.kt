package org.zamahaka.cheremosh.io

import io.reactivex.Observable
import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.model.Event

/**
 * Created by zamahaka on 28.10.2017.
 */
interface TimeLineService {

    fun loadTimeLine(timestamp: Timestamp): Observable<List<Event>>

}