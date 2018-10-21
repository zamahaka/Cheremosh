package org.zamahaka.cheremosh.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class FirebaseLiveData<T : Any?>(
        private val reference: DatabaseReference,
        private val convert: (snapshot: DataSnapshot) -> T
) : LiveData<T>() {

    private val listener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            Log.d("myLog", "onCancelled: ${p0.message}")
        }

        override fun onDataChange(snapshot: DataSnapshot) = postValue(convert(snapshot))
    }


    override fun onActive() {
        super.onActive()

        reference.addValueEventListener(listener)
    }

    override fun onInactive() {
        super.onInactive()

        reference.removeEventListener(listener)
    }

}