package smartrecycleradapter.feature

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.dragdrop.AutoDragAndDropBinder
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listener.OnClickEventListener
import smartrecycleradapter.data.MovieDataItems
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.HeaderViewHolder
import smartrecycleradapter.viewholder.ThumbViewHolder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class GridActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Grid + Drag & Drop"

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Position): Int {
                return if ((recyclerView.adapter as SmartRecyclerAdapter).getItem(position) is String) gridAutoLayoutManager.spanCount else 1
            }
        }

        val smartAdapter: SmartRecyclerAdapter = SmartRecyclerAdapter.empty()
            .map(String::class, HeaderViewHolder::class)
            .map(ComingSoonMovieModel::class, ComingSoonThumbViewHolder::class)
            .map(ActionMovieModel::class, ActionThumbViewHolder::class)
            .map(AnimatedMovieModel::class, AnimateThumbViewHolder::class)
            .map(SciFiMovieModel::class, SciFiThumbViewHolder::class)
            .setLayoutManager(gridAutoLayoutManager)
            .add(OnClickEventListener(ThumbViewHolder::class) {
                showToast("Movie ${it.position}")
            })
            .add(
                AutoDragAndDropBinder(
                longPressDragEnabled = true,
                viewHolderTypes = listOf(
                    ComingSoonThumbViewHolder::class,
                    ActionThumbViewHolder::class,
                    AnimateThumbViewHolder::class,
                    SciFiThumbViewHolder::class
                )
            ) {
                supportActionBar?.subtitle =
                    "onItemMoved from ${it.viewHolder.adapterPosition} to ${it.targetViewHolder.adapterPosition}"
            }
            )
            .into(recyclerView)

        // Set adapter data
        smartAdapter.addItem("Coming soon")
        smartAdapter.addItems(MovieDataItems.comingSoonItems.map {
            ComingSoonMovieModel(it.title, it.icon)
        })
        smartAdapter.addItem("Action")
        smartAdapter.addItems(MovieDataItems.nestedActionItems.map {
            ActionMovieModel(it.title, it.icon)
        })
        smartAdapter.addItem("Animated")
        smartAdapter.addItems(MovieDataItems.nestedAnimatedItems.map {
            AnimatedMovieModel(it.title, it.icon)
        })
        smartAdapter.addItem("Sci-Fi")
        smartAdapter.addItems(MovieDataItems.nestedSciFiItems.map {
            SciFiMovieModel(it.title, it.icon)
        })
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
