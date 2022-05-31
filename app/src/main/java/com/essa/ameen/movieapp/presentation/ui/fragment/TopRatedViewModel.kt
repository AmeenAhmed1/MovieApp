package com.essa.ameen.movieapp.presentation.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essa.ameen.movieapp.data.model.TopRatedMoviesResponse
import com.essa.ameen.movieapp.domain.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    val topRatedMoviesList: MutableLiveData<TopRatedMoviesResponse> =
        MutableLiveData()

    fun getTopRatedMovies() {
        getTopRatedMoviesUseCase.execute()
            .flowOn(Dispatchers.IO)
            .onEach {
                topRatedMoviesList.postValue(it)
            }.launchIn(viewModelScope)
    }
}