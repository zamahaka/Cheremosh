package org.zamahaka.cheremosh.model

import org.zamahaka.cheremosh.Latitude
import org.zamahaka.cheremosh.Longitude

/**
 * Created by zamahaka on 28.10.2017.
 */
data class Location(val latitude: Latitude = 0.0,
                    val longitude: Longitude = 0.0)