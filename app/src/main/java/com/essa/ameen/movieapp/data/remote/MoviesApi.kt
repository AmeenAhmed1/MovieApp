package com.essa.ameen.movieapp.data.remote

import com.essa.ameen.movieapp.core.util.ApiEndPoints
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiEndPoints.TOP_MOVIES_ENDPOINT)
    suspend fun getTopRatedMoves(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int = 1
    ): Response<TopRatedMoviesResponse>

}