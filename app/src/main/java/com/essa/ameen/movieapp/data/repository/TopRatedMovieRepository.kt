package com.essa.ameen.movieapp.data.repository

import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import javax.inject.Inject

class TopRatedMovieRepository @Inject constructor(private val api: MoviesApi) {

    suspend fun getTopRatedMovies(): TopRatedMoviesResponse {
        return api.getTopRatedMoves("cf2a0e44ebd0f153e44c7a54007b3f1c").body()!!
    }

}