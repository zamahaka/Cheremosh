package org.zamahaka.cheremosh.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.PropertyName

typealias NoteFileId = String

data class NoteFile(
        @PropertyName("name") val name: String,
        @PropertyName("file") val file: String
) : IdEntity<NoteFileId> {
    constructor() : this("", "")

    override val id: NoteFileId @Exclude get() = file
}