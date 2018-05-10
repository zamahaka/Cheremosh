package org.zamahaka.cheremosh.model

import org.zamahaka.cheremosh.Timestamp


/**
 * Created by zamahaka on 28.10.2017.
 */
data class Event(
        val name: String,
        val description: String,
        val location: Location,
        val date: Timestamp,
        val type: String,
        val visitors: List<String>
)