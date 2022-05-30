package com.essa.ameen.movieapp.domain

import com.essa.ameen.movieapp.core.ResultWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.data.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    suspend fun execute(): Flow<ResultWrapper<TopRatedMoviesResponse>> {
        return flow {
            emit(repo.getTopRatedMovies())
        }
    }
}