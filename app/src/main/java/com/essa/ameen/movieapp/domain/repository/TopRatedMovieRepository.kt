package com.essa.ameen.movieapp.domain.repository

import androidx.paging.PagingData
import com.essa.ameen.movieapp.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieRepository {
    suspend fun getTopRatedMovies(): Flow<PagingData<MovieModel>>
}