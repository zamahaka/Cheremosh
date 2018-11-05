package org.zamahaka.cheremosh.domain.repository.datasource.remote

import android.util.Log
import com.google.firebase.database.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.suspendCancellableCoroutine
import org.zamahaka.cheremosh.domain.model.Concert
import org.zamahaka.cheremosh.domain.repository.datasource.ConcertDataSource
import kotlin.coroutines.resume

private typealias ConcertService = FirebaseDatabase

class ConcertRemoteDataSource(
        private val concertService: ConcertService
) : ConcertDataSource {

    override suspend fun getConcertsList(): List<Concert> =
            concertService.reference.child("concert").awaitDataOf(emptyList())

    override suspend fun getConcert(id: Long): Concert? = null

    fun observe() = concertService.reference.child("concert").observeChanges(emptyList())
}

private suspend inline fun DatabaseReference.awaitDataOf(default: List<Concert>): List<Concert> =
        suspendCancellableCoroutine { continuation ->
            addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    if (continuation.isCancelled) return
                    continuation.resume(default)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue(
                            object : GenericTypeIndicator<List<@JvmSuppressWildcards Concert?>>() {}
                    )

                    continuation.resume(value?.filterNotNull() ?: default)
                }
            })
        }

private fun DatabaseReference.observeChanges(default: List<Concert>): ReceiveChannel<List<Concert>> {
    val channel = Channel<List<Concert>>(capacity = Channel.CONFLATED)

    addValueEventListener(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            Log.e("myLog", "onCancelled: ${p0.message}")
            channel.offer(default)
        }

        override fun onDataChange(snapshot: DataSnapshot) {
//            val value = snapshot.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Concert?>>() {})
            channel.offer(/*value?.filterNotNull() ?: */default)
        }
    })


    return channel
}