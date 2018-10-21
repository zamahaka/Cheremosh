package org.zamahaka.cheremosh.extensions

import com.google.firebase.database.DataSnapshot

inline fun <reified T : Any> DataSnapshot.listOf(): List<T> = children.mapNotNull {
    it.getValue(T::class.java)
}