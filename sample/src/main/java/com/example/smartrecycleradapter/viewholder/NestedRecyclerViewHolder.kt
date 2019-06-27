package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.extension.GridAutoLayoutManager
import com.example.smartrecycleradapter.models.ComingSoonMoviesModel
import com.example.smartrecycleradapter.models.MyWatchListModel
import com.example.smartrecycleradapter.models.NestedRecyclerViewModel
import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartAutoEventViewHolder

open class NestedRecyclerViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<NestedRecyclerViewModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.nested_recycler_view, parentView, false)),
        SmartAdapterHolder {

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view)
    private lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;

    override fun getSmartRecyclerAdapter(): SmartRecyclerAdapter {
        return smartRecyclerAdapter;
    }

    override fun setSmartAdapterBuilder(smartAdapterBuilder: SmartAdapterBuilder) {
        smartAdapterBuilder.setLayoutManager(LinearLayoutManager(recyclerView.context, HORIZONTAL, false))
        smartRecyclerAdapter = smartAdapterBuilder.into(recyclerView)
    }

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    override fun bind(item: NestedRecyclerViewModel) {
        super.bind(item)
        (recyclerView.adapter as? SmartRecyclerAdapter).let { smartAdapter ->
            (item as? ComingSoonMoviesModel)?.adapterItems.let {
                smartAdapter?.setItems(it)
            }
        }
    }
}

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {
    override fun bind(item: NestedRecyclerViewModel) {
        super.bind(item)
        (recyclerView.adapter as? SmartRecyclerAdapter).let { smartAdapter ->
            (item as? MyWatchListModel)?.adapterItems.let {
                smartAdapter?.setItems(it)
            }
        }
    }
}

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {

    private val more: TextView = itemView.findViewById(R.id.more)

    init {
        more.visibility = GONE
    }

    private lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;

    override fun getSmartRecyclerAdapter(): SmartRecyclerAdapter {
        return smartRecyclerAdapter;
    }

    override fun setSmartAdapterBuilder(smartAdapterBuilder: SmartAdapterBuilder) {
        smartAdapterBuilder.setLayoutManager(GridAutoLayoutManager(recyclerView.context, 60))
        smartRecyclerAdapter = smartAdapterBuilder.into(recyclerView)
    }
}