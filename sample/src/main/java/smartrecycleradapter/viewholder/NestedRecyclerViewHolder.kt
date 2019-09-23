package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import smartadapter.*
import smartadapter.listener.OnItemClickListener
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.NestedRecyclerViewModel

open class NestedRecyclerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<NestedRecyclerViewModel>(
        LayoutInflater.from(parentView.context).inflate(
            R.layout.nested_recycler_view,
            parentView,
            false
        )
    ), SmartAdapterHolder {

    override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
        set(value) {
            field = value
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
            recyclerView.adapter = value
        }

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.nested_recycler_view)

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
    }

    interface OnMoreButtonClickListener : OnItemClickListener {
        override val viewId: ViewId
            get() = R.id.more
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {
            override val viewHolderType: SmartViewHolderType
                get() = ComingSoonMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {
            override val viewHolderType: SmartViewHolderType
                get() = MyWatchListViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {

            override val viewHolderType: SmartViewHolderType
                get() = ActionMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {

            override val viewHolderType: SmartViewHolderType
                get() = AdventureMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {

            override val viewHolderType: SmartViewHolderType
                get() = AnimatedMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    companion object {
        inline fun onMoreButtonClickListener(
            crossinline viewEvent: (
                view: View,
                viewEventId: ViewEventId,
                position: Position
            ) -> Unit
        ) = object : OnMoreButtonClickListener {

            override val viewHolderType: SmartViewHolderType
                get() = SciFiMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
        set(value) {
            field = value
            recyclerView.layoutManager = GridAutoLayoutManager(recyclerView.context, 60)
            recyclerView.adapter = smartRecyclerAdapter
        }

    private val more: TextView = itemView.findViewById(R.id.more)

    init {
        more.visibility = GONE
    }
}
