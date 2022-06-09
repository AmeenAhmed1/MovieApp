package com.essa.ameen.movieapp.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _topRatedMovieList: MutableStateFlow<ResponseWrapper<TopRatedMoviesResponse>> =
        MutableStateFlow(ResponseWrapper.Loading)
    val topRatedMoviesList = _topRatedMovieList

    fun getTopRatedMovies() {
        getTopRatedMoviesUseCase.execute()
            .flowOn(Dispatchers.IO)
            .onEach {
                _topRatedMovieList.emit(it)
            }.launchIn(viewModelScope)
    }
}