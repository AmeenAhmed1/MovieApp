package com.essa.ameen.movieapp.di

import com.essa.ameen.movieapp.data.datasource.MoviesApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
        return client.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }
}