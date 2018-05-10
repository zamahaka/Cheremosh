package org.zamahaka.cheremosh.model

/**
 * Created by zamahaka on 28.10.2017.
 */
data class User(
        val name: String,
        val party: String,
        val visitedEvents: List<String>
)