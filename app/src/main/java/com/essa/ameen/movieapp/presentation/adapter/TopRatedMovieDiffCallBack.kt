package com.essa.ameen.movieapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.essa.ameen.movieapp.data.model.MovieModel

class TopRatedMovieDiffCallBack : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}