package org.zamahaka.cheremosh.domain.datasource

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import org.zamahaka.cheremosh.domain.FirebaseLiveData
import org.zamahaka.cheremosh.extensions.listOf
import org.zamahaka.cheremosh.extensions.map
import org.zamahaka.cheremosh.model.NoteFile
import org.zamahaka.cheremosh.model.filterValid

interface NotesFilesDataSource {

    val notes: LiveData<List<NoteFile>>

}

class NotesFilesDataSourceImpl : NotesFilesDataSource {

    override val notes: LiveData<List<NoteFile>> = FirebaseLiveData<List<NoteFile>>(
            reference = FirebaseDatabase.getInstance().getReference("notes"),
            convert = DataSnapshot::listOf
    ).map(List<NoteFile>::filterValid)

}