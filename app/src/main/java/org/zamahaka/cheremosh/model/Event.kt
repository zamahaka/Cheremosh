package org.zamahaka.cheremosh.model

import org.zamahaka.cheremosh.Timestamp


/**
 * Created by zamahaka on 28.10.2017.
 */
data class Event(val name: String = "",
                 val description: String = "",
                 val location: Location = Location(),
                 val date: Timestamp = System.currentTimeMillis(),
                 val visitors: List<String> = emptyList())