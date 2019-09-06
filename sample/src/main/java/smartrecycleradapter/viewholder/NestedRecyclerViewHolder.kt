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
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.Position
import smartadapter.listener.ViewEventId
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.NestedRecyclerViewModel
import kotlin.reflect.KClass

open class NestedRecyclerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<NestedRecyclerViewModel>(
        LayoutInflater.from(parentView.context)
            .inflate(R.layout.nested_recycler_view, parentView, false)
    ),
    SmartAdapterHolder {

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.nested_recycler_view)

    override fun setSmartRecyclerAdapter(smartRecyclerAdapter: SmartRecyclerAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
        recyclerView.adapter = smartRecyclerAdapter
    }

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
    }

    interface OnMoreButtonClickListener : OnItemClickListener {
        override val viewId: Int
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
            override val viewHolderType: KClass<out SmartViewHolder<*>>
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
            override val viewHolderType: KClass<out SmartViewHolder<*>>
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

            override val viewHolderType: KClass<out SmartViewHolder<*>>
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

            override val viewHolderType: KClass<out SmartViewHolder<*>>
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

            override val viewHolderType: KClass<out SmartViewHolder<*>>
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

            override val viewHolderType: KClass<out SmartViewHolder<*>>
                get() = SciFiMoviesViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                viewEvent(view, viewEventId, position)
            }
        }
    }
}

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {

    private val more: TextView = itemView.findViewById(R.id.more)

    init {
        more.visibility = GONE
    }

    override fun setSmartRecyclerAdapter(smartRecyclerAdapter: SmartRecyclerAdapter) {
        recyclerView.layoutManager = GridAutoLayoutManager(recyclerView.context, 60)
        recyclerView.adapter = smartRecyclerAdapter
    }
}
