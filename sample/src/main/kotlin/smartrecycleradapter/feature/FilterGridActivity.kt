package smartrecycleradapter.feature

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_filter_item.searchView
import kotlinx.android.synthetic.main.activity_filter_item.toolbarProgressBar
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension
import smartadapter.diffutil.SimpleDiffUtilExtension
import smartadapter.diffutil.extension.diffSwapList
import smartadapter.filter.FilterExtension
import smartadapter.get
import smartadapter.viewevent.listener.OnClickEventListener
import smartrecycleradapter.R
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.MovieData
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.AssetsUtils
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.SmallHeaderViewHolder
import smartrecycleradapter.viewholder.SmallThumbViewHolder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class FilterGridActivity : BaseSampleActivity() {

    private val movieData: MovieData by lazy {
        AssetsUtils.loadStyleFromAssets<MovieData>(this, "main-movie-data.json")
    }

    override val contentView: Int = R.layout.activity_filter_item
    lateinit var smartAdapter: SmartRecyclerAdapter

    private val predicate = object : DiffUtilExtension.DiffPredicate<Any> {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Filter Grid"

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 70).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Position): Int {
                    return if ((recyclerView.adapter as SmartRecyclerAdapter).getItem(position) is String) spanCount else 1
                }
            }
        }

        smartAdapter = SmartRecyclerAdapter.empty()
            .map(String::class, SmallHeaderViewHolder::class)
            .setViewTypeResolver { item, position ->
                when (item) {
                    is MovieModel -> SmallThumbViewHolder::class
                    else -> null
                }
            }
            .setLayoutManager(gridAutoLayoutManager)
            .add(OnClickEventListener(SmallThumbViewHolder::class) {
                showToast("${it.adapter.getItemCast<MovieModel>(it.position).title}\n@ ${it.position}")
            })
            .add(
                FilterExtension(
                    filterPredicate = { item, constraint ->
                        when (item) {
                            is MovieModel -> item.title.contains(constraint, true)
                            else -> true
                        }
                    },
                    targetFilterTypes = listOf(MovieModel::class),
                    loadingStateListener = {
                        toolbarProgressBar.visibility = if (it) View.VISIBLE else View.GONE
                    }
                )
            )
            .add(SimpleDiffUtilExtension(predicate) {
                toolbarProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            })
            .into(recyclerView)

        // Set search view filter
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })

        // Add a lot of adapter data
        smartAdapter.addItem("Coming soon")
        with(movieData.categories.find { it.id == "coming-soon" }!!.items) {
            repeat(100) {
                smartAdapter.addItems(shuffled())
            }
        }
        smartAdapter.addItem("Action")
        with(movieData.categories.find { it.id == "action" }!!.items) {
            repeat(100) {
                smartAdapter.addItems(shuffled())
            }
        }
        smartAdapter.addItem("Animated")
        with(movieData.categories.find { it.id == "anim" }!!.items) {
            repeat(100) {
                smartAdapter.addItems(shuffled())
            }
        }
        smartAdapter.addItem("Sci-Fi")
        with(movieData.categories.find { it.id == "sci-fi" }!!.items) {
            repeat(100) {
                smartAdapter.addItems(shuffled())
            }
        }
    }

    fun filter(query: String?) {
        val filterExtension: FilterExtension = smartAdapter.get()
        val diffExtension: SimpleDiffUtilExtension = smartAdapter.get()

        filterExtension.filter(lifecycleScope, query) {
            if (it.isSuccess) {
                smartAdapter.diffSwapList(lifecycleScope, it.getOrDefault(listOf())) {
                    supportActionBar?.subtitle = "${smartAdapter.itemCount} items filtered"
                }
            } else {
                diffExtension.cancelDiffSwapJob()
                recyclerView.scrollToPosition(0)
                supportActionBar?.subtitle = "${smartAdapter.itemCount} items filtered"
            }
        }
    }
}
