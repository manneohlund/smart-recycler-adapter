package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.listener.OnClick
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

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.nested_recycler_view)
    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    override fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
        recyclerView.adapter = smartRecyclerAdapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
    }

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
        item.items?.let {
            smartRecyclerAdapter.setItems(it)
        }
    }

    interface OnMoreButtonClickListener : OnItemClickListener {
        override val viewId: ViewId
            get() = R.id.more
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = ComingSoonMoviesViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = MyWatchListViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = ActionMoviesViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = AdventureMoviesViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = AnimatedMoviesViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    class OnMoreButtonClickListener(
        override val viewHolderType: SmartViewHolderType = SciFiMoviesViewHolder::class,
        override val listener: OnClick
    ) : NestedRecyclerViewHolder.OnMoreButtonClickListener
}

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
     private val more: TextView = itemView.findViewById(R.id.more)

    override fun initAdapter() {
        recyclerView.layoutManager = GridAutoLayoutManager(recyclerView.context, 60)
        recyclerView.adapter = smartRecyclerAdapter
        more.visibility = GONE
    }
}
