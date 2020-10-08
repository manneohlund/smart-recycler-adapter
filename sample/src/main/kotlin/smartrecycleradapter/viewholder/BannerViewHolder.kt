package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.MovieCategory
import smartrecycleradapter.utils.displayWidth

class BannerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<MovieCategory>(parentView, R.layout.banner_item) {

    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    private val requestOption = RequestOptions()
        .error(R.drawable.ic_broken_image_black_48dp)
        .override(imageView.context.displayWidth, imageView.context.displayWidth)
        .centerInside()

    override fun bind(movie: MovieCategory) {
        titleTextView.text = movie.title
        when (movie.title) {
            "" -> titleTextView.visibility = GONE
            else -> titleTextView.visibility = VISIBLE
        }

        Glide.with(imageView)
            .load(movie.items.random().iconUrl)
            .apply(requestOption)
            .into(imageView)
    }
}
