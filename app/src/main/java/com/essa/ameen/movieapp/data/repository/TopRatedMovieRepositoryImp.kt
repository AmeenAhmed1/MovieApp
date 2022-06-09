package com.essa.ameen.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.essa.ameen.movieapp.data.datasource.MovieSource
import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedMovieRepositoryImp @Inject constructor(private val api: MoviesApi) :
    TopRatedMovieRepository {

    override suspend fun getTopRatedMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false)
        ) {
            MovieSource(api)
        }.flow
    }

}