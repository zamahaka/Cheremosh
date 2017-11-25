package org.zamahaka.cheremosh.io

import com.github.b3er.rxfirebase.database.dataChangesOf
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import io.reactivex.Observable
import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.model.Event
import java.util.*

/**
 * Created by zamahaka on 28.10.2017.
 */
class FirebaseTimeLineService : TimeLineService {
    override fun loadTimeLine(timestamp: Timestamp): Observable<List<Event>> {
        return FirebaseDatabase.getInstance().reference
                .child("events")
                .child(/*timestamp*/1509140624L.toString())
                .dataChangesOf(object : GenericTypeIndicator<List<@JvmSuppressWildcards Event>>() {})
                .map { return@map if (it.isPresent) it.get() else emptyList() }
                .doOnNext { Collections.sort(it) { o1, o2 -> (o1.date - o2.date).toInt() } }

    }
}