package com.essa.ameen.movieapp.data.model

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)