package com.essa.ameen.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.essa.ameen.movieapp.core.util.PAGE_SIZE
import com.essa.ameen.movieapp.data.remote.MoviesApi
import com.essa.ameen.movieapp.data.datasource.TopRatedMoviesSource
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedMovieRepositoryImp @Inject constructor(private val api: MoviesApi) :
    TopRatedMovieRepository {

    override fun getTopRatedMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { TopRatedMoviesSource(api) }
        ).flow
    }

}