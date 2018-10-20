package org.zamahaka.cheremosh.domain.model

import org.zamahaka.cheremosh.Timestamp
import org.zamahaka.cheremosh.Url
import org.zamahaka.cheremosh.model.IdEntity
import org.zamahaka.cheremosh.model.Location

data class Concert(
        override val id: Long,
        override val location: Location,
        val name: String,
        val description: String,
        val imageUrl: Url,
        val time: Timestamp
) : IdEntity<Long>, LocationEntity {
    constructor() : this(-1, Location(), "", "", "", -1)
}