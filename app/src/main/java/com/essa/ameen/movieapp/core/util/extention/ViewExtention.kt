package com.essa.ameen.movieapp.core.util.extention

import android.view.View
import android.widget.Toast

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun Toast.showMessageToast(msg: String) {
    this.apply {
        duration = Toast.LENGTH_SHORT
        setText(msg)
    }.show()
}