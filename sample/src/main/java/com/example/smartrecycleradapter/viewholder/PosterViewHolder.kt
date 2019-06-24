package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.MovieViewModel
import com.example.smartrecycleradapter.utils.displayHeight
import com.example.smartrecycleradapter.utils.displayWidth
import smartadapter.viewholder.SmartAutoEventViewHolder

class PosterViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<MovieViewModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.poster_item, parentView, false)) {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    override fun bind(movie: MovieViewModel) {
        Glide.with(imageView)
                .load(movie.icon)
                .override(imageView.context.displayWidth, imageView.context.displayHeight)
                .centerInside()
                .into(imageView)
    }
}
