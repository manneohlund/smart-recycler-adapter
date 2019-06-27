package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.MovieBannerModel
import com.example.smartrecycleradapter.utils.displayWidth
import smartadapter.viewholder.SmartAutoEventViewHolder

class BannerViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<MovieBannerModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.banner_item, parentView, false)) {

    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    override fun bind(movie: MovieBannerModel) {
        titleTextView.text = movie.title
        when (movie.title) {
            "" -> titleTextView.visibility = GONE
            else -> titleTextView.visibility = VISIBLE
        }
        Glide.with(imageView)
                .load(movie.icon)
                .override(imageView.context.displayWidth, imageView.context.displayWidth)
                .centerInside()
                .into(imageView)
    }
}
