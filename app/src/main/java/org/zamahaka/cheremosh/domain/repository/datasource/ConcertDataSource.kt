package org.zamahaka.cheremosh.praktuka.repository.datasource

import org.zamahaka.cheremosh.praktuka.model.Concert

interface ConcertDataSource {

    suspend fun getConcertsList(): List<Concert>

    suspend fun getConcert(id: Long): Concert?

}