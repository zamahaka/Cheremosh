package org.zamahaka.cheremosh.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import org.zamahaka.cheremosh.domain.FirebaseLiveData
import org.zamahaka.cheremosh.extensions.listOf
import org.zamahaka.cheremosh.model.NoteFile

class NotesListViewModel : ViewModel() {

    val notesFiles: LiveData<List<NoteFile>> = FirebaseLiveData(
            reference = FirebaseDatabase.getInstance().getReference("notes"),
            convert = DataSnapshot::listOf
    )

}