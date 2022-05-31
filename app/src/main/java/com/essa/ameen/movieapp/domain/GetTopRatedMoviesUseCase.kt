package com.essa.ameen.movieapp.domain

import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.data.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): Flow<TopRatedMoviesResponse> {
        return flow {
            emit(repo.getTopRatedMovies())
        }
    }
}