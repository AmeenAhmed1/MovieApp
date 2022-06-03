package com.essa.ameen.movieapp.domain.repository

import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse

interface TopRatedMovieRepository {
    suspend fun getTopRatedMovies(): ResponseWrapper<TopRatedMoviesResponse>
}