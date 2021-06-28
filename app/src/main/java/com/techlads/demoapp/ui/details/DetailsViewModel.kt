package com.techlads.demoapp.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.techlads.demoapp.data.MovieRepository
import com.techlads.demoapp.model.MovieDesc
import com.techlads.demoapp.model.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class DetailsViewModel @ViewModelInject constructor(private val repository: MovieRepository):
    ViewModel() {

        private val _id = MutableLiveData<Int>()
    private val _movie: LiveData<Result<MovieDesc>> = _id.distinctUntilChanged().switchMap {
        liveData {
            repository.fetchMovie(it).onStart {
                emit(Result.loading())
            }.collect {
                emit(it)
            }
        }
    }
    val movie = _movie

    fun getMovieDetail(id: Int) {
        _id.value = id
    }

}