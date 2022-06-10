package com.essa.ameen.movieapp.core.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.essa.ameen.movieapp.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(IMAGE_BASE_URL + url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}