package org.zamahaka.cheremosh.domain

import kotlin.math.roundToInt

fun progressPercentageOf(current: Number, fromTotal: Number): Int =
        (current.toFloat() / fromTotal.toFloat())
                .coerceAtLeast(0.01f)
                .times(100)
                .roundToInt()
                .coerceAtLeast(1)