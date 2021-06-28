package com.techlads.demoapp.ui.listing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.demoapp.data.MovieRepository
import com.techlads.demoapp.model.Result
import com.techlads.demoapp.model.TrendingMovieResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListingViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository):
    ViewModel() {

    private val _movieList = MutableLiveData<Result<TrendingMovieResponse>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchTrendingMovies().collect {
                _movieList.value = it
            }
        }
    }
}