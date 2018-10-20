package org.zamahaka.cheremosh.domain.repository.datasource

import org.zamahaka.cheremosh.domain.model.Concert

interface ConcertDataSource {

    suspend fun getConcertsList(): List<Concert>

    suspend fun getConcert(id: Long): Concert?

}