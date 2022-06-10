package com.essa.ameen.movieapp.core.util

import com.essa.ameen.movieapp.BuildConfig

const val PAGE_SIZE = 20
const val INITIAL_LOADING_PAGE_NUMBER = 1
const val BASE_URL = BuildConfig.BASE_URL
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
const val API_KEY = BuildConfig.MOVIE_DB_API_KEY


//EndPoints Reference.
object ApiEndPoints {
    const val TOP_MOVIES_ENDPOINT = "movie/top_rated"
}