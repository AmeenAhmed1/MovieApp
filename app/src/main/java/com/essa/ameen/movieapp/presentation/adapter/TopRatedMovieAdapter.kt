package com.essa.ameen.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.essa.ameen.movieapp.core.util.loadImage
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.databinding.ItemMovieBinding

class TopRatedMovieAdapter :
    PagingDataAdapter<MovieModel, TopRatedMovieAdapter.MyViewHolder>(TopRatedMoviesDiff) {

    inner class MyViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemMovieBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.apply {
            currentItem?.let {
                binding.movieImage.loadImage(currentItem.poster_path)
                binding.txtMovieName.text = currentItem.title
                binding.txtRate.text = currentItem.vote_average.toString()
                binding.topRatedMovieItem.setOnClickListener {
                    onItemClickListener?.let { (currentItem) }
                }
            }
        }
    }

    private var onItemClickListener: ((MovieModel) -> Unit)? = null
    fun onItemClicked(listener: (MovieModel) -> Unit) {
        onItemClickListener = listener
    }
}