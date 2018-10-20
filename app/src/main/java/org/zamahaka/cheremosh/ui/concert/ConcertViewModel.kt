package org.zamahaka.cheremosh.ui.concert

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
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
        launch { for (event in channel) _concerts.postValue(event) }
    }

    fun refresh() {
        concertRepository.refresh()
        fetchConcerts()
    }


    private fun fetchConcerts() {
        launch(UI) { _concerts.postValue(concertRepository.getConcertsList()) }
    }

}
