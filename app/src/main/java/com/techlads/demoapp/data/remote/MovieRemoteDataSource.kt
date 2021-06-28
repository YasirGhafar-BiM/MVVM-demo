package com.techlads.demoapp.data.remote

import com.techlads.demoapp.model.MovieDesc
import com.techlads.demoapp.model.TrendingMovieResponse
import com.techlads.demoapp.model.Result
import com.techlads.demoapp.network.MoviesService
import com.techlads.demoapp.utils.ErrorUtils

import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchTrendingMovies(): Result<TrendingMovieResponse> {
        val movieService = retrofit.create(MoviesService::class.java)

        return getResponse(
            request = { movieService.getPopularMovies() },
            defaultErrorMessage = "Error fetching Movie list"
        )
    }

    suspend fun fetchMovie(id: Int): Result<MovieDesc> {
        val movieService = retrofit.create(MoviesService::class.java)

        return getResponse(
                request ={ movieService.getMovie(id) },
                defaultErrorMessage = "Error fetching Movie Description"
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String) :
        Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error<T>(errorResponse?.localizedMessage ?: defaultErrorMessage, errorResponse)
            }

        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }

}