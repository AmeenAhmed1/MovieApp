package com.essa.ameen.movieapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.essa.ameen.movieapp.BuildConfig
import com.essa.ameen.movieapp.data.model.MovieModel
import retrofit2.HttpException
import java.io.IOException

class MovieSource constructor(private val api: MoviesApi) :
    PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {

        return try {

            val pageNumber = params.key ?: 1
            val response = api.getTopRatedMoves(BuildConfig.MOVIE_DB_API_KEY, pageNumber)
            val movies = response.body()?.results ?: listOf()

            LoadResult.Page(
                data = movies,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1,
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