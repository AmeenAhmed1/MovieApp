package com.essa.ameen.movieapp.data.datasource

import com.essa.ameen.movieapp.core.ResultWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/movie/top_rated")
    fun getTopRatedMoves(
        @Query("api_key") apiKey: String
    ): Response<ResultWrapper<TopRatedMoviesResponse>>

}