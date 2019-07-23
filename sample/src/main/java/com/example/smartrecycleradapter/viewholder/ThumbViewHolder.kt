package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.MovieModel
import smartadapter.viewholder.SmartAutoEventViewHolder

private val requestOption = RequestOptions()
        .error(R.drawable.ic_broken_image_black_48dp)
        .centerInside()

class SmallThumbViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<MovieModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.thumb_small_item, parentView, false)) {

    private val imageView: ImageView = itemView as ImageView

    override fun bind(movie: MovieModel) {
        Glide.with(itemView)
                .load(movie.iconUrl)
                .apply(requestOption)
                .into(imageView)
    }

    override fun unbind() {
        Glide.with(itemView).clear(itemView)
    }
}

class ThumbViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<MovieModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.thumb_item, parentView, false)) {

    private val imageView: ImageView = itemView as ImageView

    override fun bind(movie: MovieModel) {
        Glide.with(itemView.context)
                .load(movie.iconUrl)
                .apply(requestOption)
                .into(imageView)
    }

    override fun unbind() {
        Glide.with(itemView).clear(itemView)
    }
}

class LargeThumbViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<MovieModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.thumb_large_item, parentView, false)) {

    private val imageView: ImageView = itemView as ImageView

    override fun bind(movie: MovieModel) {
        Glide.with(itemView)
                .load(movie.iconUrl)
                .apply(requestOption)
                .into(imageView)
    }

    override fun unbind() {
        Glide.with(itemView).clear(itemView)
    }
}
