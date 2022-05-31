package com.essa.ameen.movieapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.essa.ameen.movieapp.databinding.FragmentTopRatedBinding
import com.essa.ameen.movieapp.presentation.adapter.TopRatedMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

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

        topRatedViewModel.topRatedMoviesList.observe(viewLifecycleOwner) {
            recAdapter.diff.submitList(it.results)
        }
    }

    override fun onResume() {
        super.onResume()

        if (!this::recAdapter.isInitialized)
            initRecyclerView()
        else
            topRatedViewModel.getTopRatedMovies()
    }

    private fun initRecyclerView() {

        recAdapter = TopRatedMovieAdapter(requireContext())

        binding.topMovieRecycler.apply {
            adapter = recAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        topRatedViewModel.getTopRatedMovies()

        recAdapter.onItemClicked {
            TODO("Selected Item Go To Details.")
        }
    }

}