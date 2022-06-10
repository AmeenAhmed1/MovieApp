package com.essa.ameen.movieapp.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.essa.ameen.movieapp.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

//    private val _topRatedMovieList: MutableStateFlow<ResponseWrapper<TopRatedMoviesResponse>> =
//        MutableStateFlow()
//    val topRatedMoviesList = _topRatedMovieList

    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.invoke().cachedIn(viewModelScope)

}