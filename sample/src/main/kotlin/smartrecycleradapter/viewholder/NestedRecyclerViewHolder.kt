package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.nested_recycler_view.view.more
import kotlinx.android.synthetic.main.nested_recycler_view.view.nestedRecyclerView
import kotlinx.android.synthetic.main.nested_recycler_view.view.title
import smartadapter.nestedadapter.SmartNestedRecyclerViewHolder
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.MovieCategory

open class NestedRecyclerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<MovieCategory>(parentView, R.layout.nested_recycler_view),
    SmartNestedRecyclerViewHolder {

    override val recyclerView: RecyclerView = itemView.nestedRecyclerView

    override fun bind(item: MovieCategory) {
        itemView.title.text = item.title
    }
}

class ComingSoonMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class MyWatchListViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class ActionMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AdventureMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class AnimatedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class SciFiMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView)

class RecentlyPlayedMoviesViewHolder(parentView: ViewGroup) : NestedRecyclerViewHolder(parentView) {

    init {
        itemView.more.visibility = GONE
        //itemView.nestedRecyclerView.layoutManager = GridAutoLayoutManager(recyclerView.context, 60)
    }
}
