package org.zamahaka.cheremosh.praktuka.repository.datasource.remote

import android.util.Log
import com.google.firebase.database.*
import kotlinx.coroutines.experimental.NonCancellable.cancel
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import org.zamahaka.cheremosh.praktuka.model.Concert
import org.zamahaka.cheremosh.praktuka.repository.datasource.ConcertDataSource

private typealias ConcertService = FirebaseDatabase

class ConcertRemoteDataSource(
        private val concertService: ConcertService
) : ConcertDataSource {

    override suspend fun getConcertsList(): List<Concert> =
            concertService.reference.child("concert").awaitDataOf(emptyList())

    override suspend fun getConcert(id: Long): Concert? = null

    fun observe() = concertService.reference.child("concert").observeChanges(emptyList())
}

private suspend inline fun DatabaseReference.awaitDataOf(default: List<Concert>): List<Concert> = suspendCancellableCoroutine { continuation ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError?) {
            Log.e("myLog", "onCancelled: ${p0?.message}")
            if (continuation.isCancelled) return
            continuation.resume(default)
        }

        override fun onDataChange(snapshot: DataSnapshot?) {
            val value = snapshot?.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Concert?>>() {})
            continuation.resume(value?.filterNotNull() ?: default)
        }
    })

    continuation.invokeOnCompletion {
        if (continuation.isCancelled) {
            try {
                cancel()
            } catch (e: Throwable) {
                Log.e(ConcertRemoteDataSource::class.simpleName, "awaitDataOf:", e)
                Log.e("myLog", "awaitDataOf:", e)
            }
        }
    }
}

private fun DatabaseReference.observeChanges(default: List<Concert>): ReceiveChannel<List<Concert>> {
    val channel = Channel<List<Concert>>(capacity = Channel.CONFLATED)

    addValueEventListener(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError?) {
            Log.e("myLog", "onCancelled: ${p0?.message}")
            channel.offer(default)
        }

        override fun onDataChange(snapshot: DataSnapshot?) {
            val value = snapshot?.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Concert?>>() {})
            channel.offer(value?.filterNotNull() ?: default)
        }
    })


    return channel
}