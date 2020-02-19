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
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.listener.OnClick
import smartadapter.listener.OnSmartViewEvent
import smartadapter.listener.OnViewEventListener
import smartadapter.listener.ViewEvent
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.displayHeight
import smartrecycleradapter.utils.displayWidth

class PosterViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context).inflate(R.layout.poster_item, parentView, false)
), ViewEventListenerHolder<ViewEvent>, SmartAdapterHolder {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val playButton: ImageView = itemView.findViewById(R.id.playButton)
    override var viewEventListener: ViewEvent? = null
        set(value) {
            if (value is OnSmartViewEvent)
                field = value
        }
    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    init {
        playButton.setOnClickListener { playButton ->
            (viewEventListener!! as OnSmartViewEvent).event.invoke(playButton, R.id.event_play, smartRecyclerAdapter, adapterPosition)
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
    internal open class OnItemClickListener(
        override val viewHolderType: SmartViewHolderType = PosterViewHolder::class,
        override val listener: OnClick
    ) : smartadapter.listener.OnItemClickListener

    internal class OnPlayButtonClickListener(
        override val viewHolderType: SmartViewHolderType = PosterViewHolder::class,
        override val listener: OnSmartViewEvent
    ) : OnViewEventListener<OnSmartViewEvent>

    internal  class OnStarButtonClickListener(
        override val viewId: ViewId = R.id.starButton,
        override val listener: OnClick
    ) : OnItemClickListener(listener = listener)

    internal class OnInfoButtonClickListener(
        override val viewId: ViewId = R.id.infoButton,
        override val listener: OnClick
    ) : OnItemClickListener(listener = listener)
}
