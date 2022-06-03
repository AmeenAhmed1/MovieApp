package com.essa.ameen.movieapp.domain.usecase

import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): Flow<ResponseWrapper<TopRatedMoviesResponse>> {
        return flow {
            emit(repo.getTopRatedMovies())
        }
    }
}