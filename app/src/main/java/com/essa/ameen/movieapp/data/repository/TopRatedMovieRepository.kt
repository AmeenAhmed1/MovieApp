package com.essa.ameen.movieapp.data.repository

import com.essa.ameen.movieapp.core.ResultWrapper
import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import javax.inject.Inject

class TopRatedMovieRepository @Inject constructor(private val api: MoviesApi) {

    suspend fun getTopRatedMovies(): ResultWrapper<TopRatedMoviesResponse> {
        return api.getTopRatedMoves("kkj").body()!!
    }

}