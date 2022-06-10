package com.essa.ameen.movieapp.domain.usecase

import androidx.paging.PagingData
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    operator fun invoke(): Flow<PagingData<MovieModel>> {
        return repo.getTopRatedMovies()
    }
}