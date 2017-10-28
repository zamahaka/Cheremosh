package org.zamahaka.cheremosh.io

import com.github.b3er.rxfirebase.database.dataChanges
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import io.reactivex.Observable
import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.model.Event

/**
 * Created by zamahaka on 28.10.2017.
 */
class FirebaseTimeLineService : TimeLineService {
    override fun loadTimeLine(timestamp: Timestamp): Observable<List<Event>> { //TODO convert to 0h:0m:0s
        return FirebaseDatabase.getInstance().reference
                .child("events")
                .child(/*timestamp*/1509140624L.toString())
                .dataChanges()
                .map {
                    if (it.exists()) {
                        return@map it.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Event>>() {})
                    } else emptyList<Event>()
                }
    }
}