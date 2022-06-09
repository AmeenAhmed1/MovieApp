package com.essa.ameen.movieapp.domain.usecase

import androidx.paging.PagingData
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): Flow<PagingData<MovieModel>> {
        return flow { repo.getTopRatedMovies() }
    }
}