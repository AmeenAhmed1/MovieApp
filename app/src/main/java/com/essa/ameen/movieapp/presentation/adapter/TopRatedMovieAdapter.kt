package com.essa.ameen.movieapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.essa.ameen.movieapp.R
import com.essa.ameen.movieapp.core.util.loadImage
import com.essa.ameen.movieapp.data.model.MovieModel
import com.essa.ameen.movieapp.databinding.ItemMovieBinding

class TopRatedMovieAdapter(val context: Context) :
    RecyclerView.Adapter<TopRatedMovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemMovieBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = diff.currentList[position]

        holder.apply {
            binding.movieImage.loadImage(currentItem.poster_path)
            binding.txtMovieName.text = currentItem.title
            binding.txtRate.text = currentItem.vote_average.toString()
            binding.topRatedMovieItem.setOnClickListener {
                onItemClickListener?.let { (currentItem) }
            }
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((MovieModel) -> Unit)? = null
    fun onItemClicked(listener: (MovieModel) -> Unit) {
        onItemClickListener = listener
    }
}