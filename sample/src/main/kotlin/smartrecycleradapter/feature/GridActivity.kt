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
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.MovieData
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.AssetsUtils
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.HeaderViewHolder
import smartrecycleradapter.viewholder.ThumbViewHolder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class GridActivity : BaseSampleActivity() {

    private val movieData: MovieData by lazy {
        AssetsUtils.loadStyleFromAssets<MovieData>(this, "main-movie-data.json")
    }

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
            .setViewTypeResolver { item, position ->
                when (item) {
                    is MovieModel -> when (item.category) {
                        "coming-soon" -> ComingSoonThumbViewHolder::class
                        "action" -> ActionThumbViewHolder::class
                        "anim" -> AnimateThumbViewHolder::class
                        "sci-fi" -> SciFiThumbViewHolder::class
                        else -> null
                    }
                    else -> null
                }
            }
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
        smartAdapter.addItems(movieData.categories.find { it.id == "coming-soon" }!!.items)
        smartAdapter.addItem("Action")
        smartAdapter.addItems(movieData.categories.find { it.id == "action" }!!.items)
        smartAdapter.addItem("Animated")
        smartAdapter.addItems(movieData.categories.find { it.id == "anim" }!!.items)
        smartAdapter.addItem("Sci-Fi")
        smartAdapter.addItems(movieData.categories.find { it.id == "sci-fi" }!!.items)
    }

    class ComingSoonThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class ActionThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class AnimateThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
    class SciFiThumbViewHolder(parentView: ViewGroup) : ThumbViewHolder(parentView)
}
