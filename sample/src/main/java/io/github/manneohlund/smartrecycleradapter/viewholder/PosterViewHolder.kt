package io.github.manneohlund.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright © 2019. All rights reserved.
 */

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.manneohlund.smartrecycleradapter.R
import io.github.manneohlund.smartrecycleradapter.models.MovieModel
import io.github.manneohlund.smartrecycleradapter.utils.displayHeight
import io.github.manneohlund.smartrecycleradapter.utils.displayWidth
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewEventListenerHolder
import smartadapter.viewholder.SmartViewHolder

class PosterViewHolder(parentView: View) : SmartViewHolder<MovieModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.poster_item,  parentView as ViewGroup, false)),
        SmartViewEventListenerHolder {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    private val playButton: ImageView = itemView.findViewById(R.id.playButton)
    private var viewActionListener: OnViewEventListener? = null;

    override fun setOnViewEventListener(viewActionListener: OnViewEventListener) {
        Log.e("PosterViewHolder", "::::::::::::::::::::::::::: setOnViewEventListener")
        this.viewActionListener = viewActionListener;

        playButton.setOnClickListener { playButton ->
            viewActionListener.onViewEvent(playButton, R.id.event_play, adapterPosition)
        }
    }

    private val requestOption = RequestOptions()
            .error(R.drawable.ic_broken_image_black_48dp)
            .centerInside()

    override fun bind(movie: MovieModel) {
        Glide.with(imageView)
                .load(movie.iconUrl)
                .apply(requestOption)
                .override(imageView.context.displayWidth, imageView.context.displayHeight)
                .centerInside()
                .into(imageView)
    }

    override fun unbind() {
        Glide.with(imageView).clear(imageView)
    }

    // Event listeners
    internal interface OnItemClickListener : smartadapter.listener.OnItemClickListener {

        @JvmDefault
        override fun getViewHolderType() = PosterViewHolder::class.java
    }

    internal interface OnPlayButtonClickListener : OnItemClickListener {

        @JvmDefault
        override fun getViewId(): Int = R.id.playButton
    }

    internal interface OnStarButtonClickListener : OnItemClickListener {

        @JvmDefault
        override fun getViewId(): Int = R.id.starButton
    }

    internal interface OnInfoButtonClickListener : OnItemClickListener {

        @JvmDefault
        override fun getViewId(): Int = R.id.infoButton
    }
}
