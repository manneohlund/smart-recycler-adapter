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
import com.example.smartrecycleradapter.models.NestedRecyclerViewModel
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartAutoEventViewHolder

open class NestedRecyclerViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<NestedRecyclerViewModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.nested_recycler_view, parentView, false)),
        SmartAdapterHolder {

    private val title: TextView = itemView.findViewById(R.id.title)
    protected val recyclerView: RecyclerView = itemView.findViewById(R.id.nested_recycler_view)

    override fun setSmartRecyclerAdapter(smartRecyclerAdapter: SmartRecyclerAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
        recyclerView.adapter = smartRecyclerAdapter as RecyclerView.Adapter<*>
    }

    override fun bind(item: NestedRecyclerViewModel) {
        title.text = item.title
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView);

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView);

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {

    private val more: TextView = itemView.findViewById(R.id.more)

    init {
        more.visibility = GONE
    }

    override fun setSmartRecyclerAdapter(smartRecyclerAdapter: SmartRecyclerAdapter) {
        recyclerView.layoutManager = GridAutoLayoutManager(recyclerView.context, 60)
        recyclerView.adapter = smartRecyclerAdapter as RecyclerView.Adapter<*>
    }
}