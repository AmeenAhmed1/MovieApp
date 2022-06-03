package com.essa.ameen.movieapp.di

import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.essa.ameen.movieapp.data.repository.TopRatedMovieRepositoryImp
import com.essa.ameen.movieapp.domain.repository.TopRatedMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTopRatedMovieRepository(api: MoviesApi) =
        TopRatedMovieRepositoryImp(api) as TopRatedMovieRepository

}