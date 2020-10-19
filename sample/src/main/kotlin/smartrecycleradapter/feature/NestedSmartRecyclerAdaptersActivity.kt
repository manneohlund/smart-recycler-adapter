package smartrecycleradapter.feature

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.nestedadapter.SmartNestedAdapterBinder
import smartadapter.viewevent.dragdrop.AutoDragAndDropBinder
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnLongClickEventListener
import smartrecycleradapter.BuildConfig
import smartrecycleradapter.R
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.CopyrightModel
import smartrecycleradapter.models.MovieCategory
import smartrecycleradapter.models.MovieData
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.utils.AssetsUtils
import smartrecycleradapter.viewholder.ActionMoviesViewHolder
import smartrecycleradapter.viewholder.AdventureMoviesViewHolder
import smartrecycleradapter.viewholder.AnimatedMoviesViewHolder
import smartrecycleradapter.viewholder.BannerViewHolder
import smartrecycleradapter.viewholder.ComingSoonMoviesViewHolder
import smartrecycleradapter.viewholder.CopyrightViewHolder
import smartrecycleradapter.viewholder.LargeThumbViewHolder
import smartrecycleradapter.viewholder.MyWatchListViewHolder
import smartrecycleradapter.viewholder.NestedRecyclerViewHolder
import smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder
import smartrecycleradapter.viewholder.SciFiMoviesViewHolder
import smartrecycleradapter.viewholder.SmallThumbViewHolder
import smartrecycleradapter.viewholder.ThumbViewHolder
import java.util.Locale

/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class NestedSmartRecyclerAdaptersActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Nested SmartRecyclerAdapters"

        initSmartRecyclerAdapter()
    }

    private fun initSmartRecyclerAdapter() {
        val movieData: MovieData = AssetsUtils.loadStyleFromAssets(this, "simple-movie-data.json")
        val items = movieData.categories as MutableList<Any>
        items.add(
            CopyrightModel(
                "SmartRecyclerAdapter v${BuildConfig.VERSION_NAME}\n\nDeveloped by Manne Öhlund"
            )
        )

        SmartRecyclerAdapter
            .items(items)
            .map(CopyrightModel::class, CopyrightViewHolder::class)
            .setViewTypeResolver { item, position ->
                when (item) {
                    is MovieCategory -> when (item.type) {
                        "movie" -> when (item.id) {
                            "coming-soon" -> ComingSoonMoviesViewHolder::class
                            "watch-list" -> MyWatchListViewHolder::class
                            "action" -> ActionMoviesViewHolder::class
                            "adventure" -> AdventureMoviesViewHolder::class
                            "anim" -> AnimatedMoviesViewHolder::class
                            "sci-fi" -> SciFiMoviesViewHolder::class
                            "recent" -> RecentlyPlayedMoviesViewHolder::class
                            else -> null
                        }
                        "banner" -> BannerViewHolder::class
                        else -> null
                    }
                    else -> null
                }
            }
            .setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false).apply {
                isItemPrefetchEnabled = true
                initialPrefetchItemCount = 20
            })
            .add(
                SmartNestedAdapterBinder(
                    viewHolderType = NestedRecyclerViewHolder::class,
                    recyclerViewBinder = { viewHolder, recyclerView ->
                        when (viewHolder) {
                            is RecentlyPlayedMoviesViewHolder -> recyclerView.layoutManager = GridAutoLayoutManager(this, 60)
                            else -> recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false).apply {
                                isItemPrefetchEnabled = true
                                initialPrefetchItemCount = 20
                            }
                        }
                        recyclerView.setHasFixedSize(true)
                        recyclerView.isNestedScrollingEnabled = false
                    },
                    smartRecyclerAdapterBuilder = SmartRecyclerAdapter.empty()
                        .map(MovieModel::class, ThumbViewHolder::class)
                        .setViewTypeResolver { item, position ->
                            when (item) {
                                is MovieModel -> {
                                    when (item.size) {
                                        MovieModel.LARGE -> LargeThumbViewHolder::class
                                        MovieModel.SMALL -> SmallThumbViewHolder::class
                                        else -> ThumbViewHolder::class
                                    }
                                }
                                else -> null
                            }
                        }
                        .add(OnClickEventListener {
                            showToast(
                                "%s \nindex %d",
                                getMovieTitle(it.adapter, it.position),
                                it.position
                            )
                        })
                        .add(OnLongClickEventListener {
                            showToast(
                                "%s \nindex: %d",
                                getMovieTitle(it.adapter, it.position),
                                it.position
                            )
                            it.adapter.removeItem(it.position)
                        })
                        .add(AutoDragAndDropBinder(
                            dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                            longPressDragEnabled = true,
                            viewHolderTypes = listOf(
                                ThumbViewHolder::class
                            )
                        ) {

                        })
                )
            )
            .add(OnClickEventListener(NestedRecyclerViewHolder::class, R.id.more) {
                showToast("More clicked at ${it.position}")
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }

    private fun getMovieTitle(
        smartRecyclerAdapter: SmartRecyclerAdapter,
        position: Position
    ): String {
        val movieModel = smartRecyclerAdapter.getItem(position) as MovieModel
        return movieModel.title
    }

    fun showToast(format: String, vararg args: Any) {
        Toast.makeText(
            this,
            String.format(Locale.ENGLISH, format, *args),
            Toast.LENGTH_LONG
        ).show()
    }
}
