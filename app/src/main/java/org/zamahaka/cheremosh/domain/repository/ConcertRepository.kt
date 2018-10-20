package org.zamahaka.cheremosh.domain.repository

import org.zamahaka.cheremosh.domain.model.Concert
import org.zamahaka.cheremosh.domain.repository.datasource.ConcertDataSource
import org.zamahaka.cheremosh.domain.repository.datasource.remote.ConcertRemoteDataSource

class ConcertRepository(
        private val remote: ConcertRemoteDataSource
) : ConcertDataSource, Repository {

    private var cache: List<Concert> = emptyList()

    private var cacheIsDirty = true

    override suspend fun getConcertsList(): List<Concert> {
        if (cacheIsDirty) {
            val remotes = remote.getConcertsList()
            cache = remotes
            cacheIsDirty = false
        }

        return cache
    }

    override suspend fun getConcert(id: Long): Concert? = null

    override fun refresh() {
        cacheIsDirty = true
    }
}