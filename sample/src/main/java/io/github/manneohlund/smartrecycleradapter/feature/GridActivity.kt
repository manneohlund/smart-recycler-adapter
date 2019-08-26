package io.github.manneohlund.smartrecycleradapter.feature

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import io.github.manneohlund.smartrecycleradapter.data.MovieDataItems
import io.github.manneohlund.smartrecycleradapter.extension.GridAutoLayoutManager
import io.github.manneohlund.smartrecycleradapter.models.MovieModel
import io.github.manneohlund.smartrecycleradapter.viewholder.HeaderViewHolder
import io.github.manneohlund.smartrecycleradapter.viewholder.ThumbViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.widget.AutoDragAndDropExtension
import smartadapter.widget.DragAndDropExtensionBuilder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class GridActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Grid + Drag & Drop";

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((recyclerView.adapter as SmartRecyclerAdapter).getItem(position) is String) gridAutoLayoutManager.spanCount else 1
            }
        }

        val smartAdapter: SmartRecyclerAdapter = SmartRecyclerAdapter.empty()
                .map(String::class.java, HeaderViewHolder::class.java)
                .map(ComingSoonMovieModel::class.java, ComingSoonThumbViewHolder::class.java)
                .map(ActionMovieModel::class.java, ActionThumbViewHolder::class.java)
                .map(AnimatedMovieModel::class.java, AnimateThumbViewHolder::class.java)
                .map(SciFiMovieModel::class.java, SciFiThumbViewHolder::class.java)
                .setLayoutManager(gridAutoLayoutManager)
                .addViewEventListener(object : ThumbViewHolder.OnItemClickListener {
                    override fun onViewEvent(view: View, actionId: Int, position: Int) {
                        Toast.makeText(applicationContext, "Movie $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .addExtensionBuilder(DragAndDropExtensionBuilder(AutoDragAndDropExtension())
                        .setLongPressDragEnabled(true)
                        .setViewHolderTypes(
                                ComingSoonThumbViewHolder::class.java,
                                ActionThumbViewHolder::class.java,
                                AnimateThumbViewHolder::class.java,
                                SciFiThumbViewHolder::class.java)
                        .setOnItemMovedListener { oldViewHolder, targetViewHolder ->
                            Toast.makeText(applicationContext, "onItemMoved from ${oldViewHolder.adapterPosition} to ${targetViewHolder.adapterPosition}", Toast.LENGTH_SHORT).show()
                        })
                .into(recyclerView)

        // Set adapter data
        smartAdapter.addItem("Coming soon")
        smartAdapter.addItems(MovieDataItems.comingSoonItems.map { ComingSoonMovieModel(it.title, it.icon) })
        smartAdapter.addItem("Action")
        smartAdapter.addItems(MovieDataItems.nestedActionItems.map { ActionMovieModel(it.title, it.icon) })
        smartAdapter.addItem("Animated")
        smartAdapter.addItems(MovieDataItems.nestedAnimatedItems.map { AnimatedMovieModel(it.title, it.icon) })
        smartAdapter.addItem("Sci-Fi")
        smartAdapter.addItems(MovieDataItems.nestedSciFiItems.map { SciFiMovieModel(it.title, it.icon) })
    }

    class ComingSoonMovieModel(title: String, icon: String) : MovieModel(title, icon)
    class ActionMovieModel(title: String, icon: String) : MovieModel(title, icon)
    class AnimatedMovieModel(title: String, icon: String) : MovieModel(title, icon)
    class SciFiMovieModel(title: String, icon: String) : MovieModel(title, icon)

    class ComingSoonThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class ActionThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class AnimateThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class SciFiThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
}
