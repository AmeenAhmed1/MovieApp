package com.essa.ameen.movieapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.essa.ameen.movieapp.core.util.RECYCLER_VIEW_GRID_SPAN_SIZE
import com.essa.ameen.movieapp.core.util.extention.hideView
import com.essa.ameen.movieapp.core.util.extention.showView
import com.essa.ameen.movieapp.databinding.FragmentTopRatedBinding
import com.essa.ameen.movieapp.presentation.adapter.TopRatedMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentTopRatedBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: TopRatedMovieAdapter
    private val topRatedViewModel: TopRatedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()

    }

    private fun initObservers() {

        lifecycleScope.launchWhenCreated {
            topRatedViewModel.getTopRatedMovies().collectLatest { pagingData ->
                recAdapter.submitData(pagingData)
            }
        }

//        lifecycleScope.launchWhenCreated {
//            topRatedViewModel.topRatedMoviesList.collect() {
//                when (it) {
//                    is ResponseWrapper.Success -> recAdapter.diff.submitList(it.value.results)
//                    is ResponseWrapper.Fail -> {
//                        TODO("Handle Error && Failure")
//                    }
//                }
//            }
//        }
    }

    private fun initRecyclerView() {

        if (!this::recAdapter.isInitialized) {

            recAdapter = TopRatedMovieAdapter()

            binding.topMovieRecycler.apply {
                adapter = recAdapter
                layoutManager = GridLayoutManager(requireContext(), RECYCLER_VIEW_GRID_SPAN_SIZE)
            }

            topRatedViewModel.getTopRatedMovies()

            recAdapter.onItemClicked {
                TODO("Selected Item Go To Details.")
            }

            recAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                    binding.loadingProgress.showView()
                else {
                    binding.loadingProgress.hideView()

                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        } else
            topRatedViewModel.getTopRatedMovies()


    }

}