package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright © 2019. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.displayHeight
import smartrecycleradapter.utils.displayWidth

class PosterViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context).inflate(R.layout.poster_item, parentView, false)
), ViewEventListenerHolder {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    private val playButton: ImageView = itemView.findViewById(R.id.playButton)
    private var viewEventListener: OnViewEventListener? = null

    override fun setOnViewEventListener(viewEventListener: OnViewEventListener) {
        this.viewEventListener = viewEventListener

        playButton.setOnClickListener { playButton ->
            viewEventListener.onViewEvent(playButton, R.id.event_play, adapterPosition)
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
        override val viewHolderType: SmartViewHolderType
            get() = PosterViewHolder::class
    }

    internal interface OnPlayButtonClickListener : OnItemClickListener {
        override val viewId: ViewId
            get() = R.id.playButton
    }

    internal interface OnStarButtonClickListener : OnItemClickListener {
        override val viewId: ViewId
            get() = R.id.starButton
    }

    internal interface OnInfoButtonClickListener : OnItemClickListener {
        override val viewId: ViewId
            get() = R.id.infoButton
    }
}
