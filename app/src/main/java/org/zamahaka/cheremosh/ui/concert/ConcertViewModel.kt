package org.zamahaka.cheremosh.ui.concert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.zamahaka.cheremosh.domain.model.Concert
import org.zamahaka.cheremosh.domain.repository.ConcertRepository
import org.zamahaka.cheremosh.domain.repository.datasource.remote.ConcertRemoteDataSource

class ConcertViewModel(
        private val concertRepository: ConcertRepository,
        private val remote: ConcertRemoteDataSource
) : ViewModel() {

    private val _concerts = MutableLiveData<List<Concert>>()
    val concerts: LiveData<List<Concert>> = _concerts

    init {
        val channel = remote.observe()
        GlobalScope.launch(Dispatchers.IO) {
            for (event in channel) _concerts.postValue(event)
        }
    }

    fun refresh() {
        concertRepository.refresh()
        fetchConcerts()
    }


    private fun fetchConcerts() {
        GlobalScope.launch(Dispatchers.IO) { _concerts.postValue(concertRepository.getConcertsList()) }
    }

}
