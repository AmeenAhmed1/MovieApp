package com.essa.ameen.movieapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.essa.ameen.movieapp.core.util.API_KEY
import com.essa.ameen.movieapp.core.util.INITIAL_LOADING_PAGE_NUMBER
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.data.remote.MoviesApi
import retrofit2.HttpException
import java.io.IOException

class TopRatedMoviesSource constructor(private val api: MoviesApi) :
    PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {

        val pageNumber = params.key ?: INITIAL_LOADING_PAGE_NUMBER

        return try {
            val response = api.getTopRatedMoves(API_KEY, pageNumber)
            val movies = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = movies,
                nextKey = if (movies.isEmpty()) null else pageNumber.plus(1),
                prevKey = null
            )

        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}