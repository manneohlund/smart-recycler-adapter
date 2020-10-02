package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.MovieModel

private val requestOption = RequestOptions()
    .error(R.drawable.ic_broken_image_black_48dp)
    .transform(CenterCrop(), RoundedCorners(8))

class SmallThumbViewHolder(parentView: ViewGroup) :
    SmartViewHolder<MovieModel>(parentView, R.layout.thumb_small_item) {

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

open class ThumbViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context)
        .inflate(R.layout.thumb_item, parentView, false)
) {

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

class LargeThumbViewHolder(parentView: ViewGroup) :
    SmartViewHolder<MovieModel>(parentView, R.layout.thumb_large_item) {

    private val imageView: ImageView = itemView as ImageView

    override fun bind(item: MovieModel) {
        Glide.with(itemView)
            .load(item.iconUrl)
            .apply(requestOption)
            .into(imageView)
    }

    override fun unbind() {
        Glide.with(itemView).clear(itemView)
    }
}
