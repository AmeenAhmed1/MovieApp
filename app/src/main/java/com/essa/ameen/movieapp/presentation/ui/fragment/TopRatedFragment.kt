package com.essa.ameen.movieapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.essa.ameen.movieapp.core.wrapper.ResponseWrapper
import com.essa.ameen.movieapp.databinding.FragmentTopRatedBinding
import com.essa.ameen.movieapp.presentation.adapter.TopRatedMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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

        initObservers()
        initRecyclerView()

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            topRatedViewModel.topRatedMoviesList.collect() {
                when (it) {
                    is ResponseWrapper.Success -> recAdapter.diff.submitList(it.value.results)
                    is ResponseWrapper.Fail -> {
                        TODO("Handle Error && Failure")
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {

        if (!this::recAdapter.isInitialized) {

            recAdapter = TopRatedMovieAdapter(requireContext())

            binding.topMovieRecycler.apply {
                adapter = recAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }

            topRatedViewModel.getTopRatedMovies()

            recAdapter.onItemClicked {
                TODO("Selected Item Go To Details.")
            }

        } else
            topRatedViewModel.getTopRatedMovies()


    }

}