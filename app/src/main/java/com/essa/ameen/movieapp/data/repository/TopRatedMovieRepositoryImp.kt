package com.essa.ameen.movieapp.data.repository

import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import javax.inject.Inject

class TopRatedMovieRepositoryImp @Inject constructor(private val api: MoviesApi) :
    TopRatedMovieRepository {

    override suspend fun getTopRatedMovies(): ResponseWrapper<TopRatedMoviesResponse> {

        return try {
            val response = api.getTopRatedMoves("")
            if (response.isSuccessful) {
                response.body()?.let {
                    return ResponseWrapper.Success(it)
                } ?: ResponseWrapper.Fail("")
            } else {
                ResponseWrapper.Fail(response.errorBody().toString())
            }

        } catch (e: Exception) {
            ResponseWrapper.Fail("")
        }

        //return api.getTopRatedMoves("cf2a0e44ebd0f153e44c7a54007b3f1c").body()!!
    }

}