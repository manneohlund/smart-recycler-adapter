package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.NestedRecyclerViewModel

open class NestedRecyclerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<NestedRecyclerViewModel>(parentView, R.layout.nested_recycler_view),
    SmartAdapterHolder {

    override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
        set(value) {
            field = value
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
            recyclerView.adapter = value
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.setHasFixedSize(true)
        }

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.nested_recycler_view)

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

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
