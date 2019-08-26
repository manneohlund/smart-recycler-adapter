package io.github.manneohlund.smartrecycleradapter.viewholder

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
import com.bumptech.glide.request.RequestOptions
import io.github.manneohlund.smartrecycleradapter.R
import io.github.manneohlund.smartrecycleradapter.models.MovieBannerModel
import io.github.manneohlund.smartrecycleradapter.utils.displayWidth
import smartadapter.viewholder.SmartViewHolder

class BannerViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieBannerModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.banner_item, parentView, false)) {

    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    private val requestOption = RequestOptions()
            .error(R.drawable.ic_broken_image_black_48dp)
            .override(imageView.context.displayWidth, imageView.context.displayWidth)
            .centerInside()

    override fun bind(movie: MovieBannerModel) {
        titleTextView.text = movie.title
        when (movie.title) {
            "" -> titleTextView.visibility = GONE
            else -> titleTextView.visibility = VISIBLE
        }

        Glide.with(imageView)
                .load(movie.iconUrl)
                .apply(requestOption)
                .into(imageView)
    }
}
