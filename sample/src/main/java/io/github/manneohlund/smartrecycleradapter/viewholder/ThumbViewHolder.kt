package io.github.manneohlund.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.manneohlund.smartrecycleradapter.R
import io.github.manneohlund.smartrecycleradapter.models.MovieModel
import smartadapter.viewholder.SmartViewHolder

typealias SmartViewHolderClass = Class<out SmartViewHolder<*>>

private val requestOption = RequestOptions()
        .error(R.drawable.ic_broken_image_black_48dp)
        .centerInside()

class SmallThumbViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
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

    internal interface OnItemClickListener : smartadapter.listener.OnItemClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = SmallThumbViewHolder::class.java
    }

    internal interface OnItemLongClickListener : smartadapter.listener.OnItemLongClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = SmallThumbViewHolder::class.java
    }
}

open class ThumbViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
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

    internal interface OnItemClickListener : smartadapter.listener.OnItemClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = ThumbViewHolder::class.java
    }

    internal interface OnItemLongClickListener : smartadapter.listener.OnItemLongClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = ThumbViewHolder::class.java
    }
}

class LargeThumbViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
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

    internal interface OnItemClickListener : smartadapter.listener.OnItemClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = LargeThumbViewHolder::class.java
    }

    internal interface OnItemLongClickListener : smartadapter.listener.OnItemLongClickListener {

        @JvmDefault
        override fun getViewHolderType(): SmartViewHolderClass = LargeThumbViewHolder::class.java
    }
}
