package org.zamahaka.cheremosh.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.google.firebase.database.*
import org.zamahaka.cheremosh.model.NoteFile

class NotesListViewModel(

) : ViewModel() {

    val notesFiles = MutableLiveData<List<NoteFile>>().apply {
        value = emptyList()
    }

    init {
        FirebaseDatabase.getInstance().getReference("notes").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("myLog", "onCancelled: ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val notes = p0.getValue(
                        object : GenericTypeIndicator<List<@JvmSuppressWildcards NoteFile?>>() {}
                )?.filterNotNull().orEmpty()
                notesFiles.postValue(notes)
            }
        })
    }

}