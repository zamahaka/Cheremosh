package org.zamahaka.cheremosh.domain

import kotlin.math.roundToInt

fun progressPercentage(of: Number, from: Number): Int =
        (of.toFloat() / from.toFloat() * 100)
                .roundToInt()
                .coerceAtLeast(0)
                .coerceAtMost(100)