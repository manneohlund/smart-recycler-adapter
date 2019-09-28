package smartrecycleradapter.feature

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.Position
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewEventId
import smartrecycleradapter.BuildConfig
import smartrecycleradapter.DemoActivity.Companion.getActionName
import smartrecycleradapter.R
import smartrecycleradapter.data.MovieDataItems
import smartrecycleradapter.models.*
import smartrecycleradapter.viewholder.*
import java.util.*

/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class NestedSmartRecyclerAdaptersActivity : BaseSampleActivity() {

    lateinit var comingSoonSmartMovieAdapter: SmartEndlessScrollRecyclerAdapter
    private lateinit var myWatchListSmartMovieAdapter: SmartRecyclerAdapter
    private lateinit var actionMoviesSmartMovieAdapter: SmartRecyclerAdapter
    private lateinit var adventuresMoviesSmartMovieAdapter: SmartRecyclerAdapter
    private lateinit var animatedMoviesSmartMovieAdapter: SmartRecyclerAdapter
    private lateinit var sciFiMoviesSmartMovieAdapter: SmartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Nested SmartRecyclerAdapters"

        initNestedSmartRecyclerAdapters()
        initSmartRecyclerAdapter()
    }

    private fun initSmartRecyclerAdapter() {
        val items = mutableListOf(
            ComingSoonMoviesModel("Coming soon"),
            MyWatchListModel("My watch list"),
            ActionMoviesModel("Action"),
            AdventureMoviesModel("Adventure"),
            AnimatedMoviesModel("Animated"),
            SciFiMoviesModel("Sci-Fi"),
            CopyrightModel(
                String.format(
                    "SmartRecyclerAdapter v%s\n\nDeveloped by Manne Öhlund",
                    BuildConfig.VERSION_NAME
                )
            )
        )

        SmartRecyclerAdapter
            .items(items)
            .map(MoviePosterModel::class, PosterViewHolder::class)
            .map(ComingSoonMoviesModel::class, ComingSoonMoviesViewHolder::class)
            .map(MyWatchListModel::class, MyWatchListViewHolder::class)
            .map(ActionMoviesModel::class, ActionMoviesViewHolder::class)
            .map(AdventureMoviesModel::class, AdventureMoviesViewHolder::class)
            .map(AnimatedMoviesModel::class, AnimatedMoviesViewHolder::class)
            .map(SciFiMoviesModel::class, SciFiMoviesViewHolder::class)
            .map(RecentlyPlayedMoviesModel::class, RecentlyPlayedMoviesViewHolder::class)
            .map(CopyrightModel::class, CopyrightViewHolder::class)

            // Map nested SmartRecyclerAdapter
            .map(ComingSoonMoviesViewHolder::class, comingSoonSmartMovieAdapter)
            .map(MyWatchListViewHolder::class, myWatchListSmartMovieAdapter)
            .map(ActionMoviesViewHolder::class, actionMoviesSmartMovieAdapter)
            .map(AdventureMoviesViewHolder::class, adventuresMoviesSmartMovieAdapter)
            .map(AnimatedMoviesViewHolder::class, animatedMoviesSmartMovieAdapter)
            .map(SciFiMoviesViewHolder::class, sciFiMoviesSmartMovieAdapter)

            .into<SmartRecyclerAdapter>(recyclerView)
    }

    private var moreItemsLoadedCount = 0
    private fun initNestedSmartRecyclerAdapters() {
        val onThumbnailClickListener = object : ThumbViewHolder.OnItemClickListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                showToast(
                    "Coming soon \n%s \n%s index: %d",
                    getMovieTitle(comingSoonSmartMovieAdapter, position),
                    getActionName(viewEventId),
                    position
                )
            }
        }

        comingSoonSmartMovieAdapter =
            SmartEndlessScrollRecyclerAdapter.items(MovieDataItems.comingSoonItems)
                .map(MovieModel::class, LargeThumbViewHolder::class)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        // Set custom load more view
        comingSoonSmartMovieAdapter.setCustomLoadMoreLayoutResource(R.layout.custom_loadmore_view)

        // Pagination ends after 3 loads
        comingSoonSmartMovieAdapter.setOnLoadMoreListener {
            Handler().postDelayed({
                comingSoonSmartMovieAdapter.addItems(
                    comingSoonSmartMovieAdapter.itemCount - 1,
                    MovieDataItems.loadMoreItems
                )
                if (moreItemsLoadedCount++ == 2)
                    comingSoonSmartMovieAdapter.isEndlessScrollEnabled = false
            }, 1000)
        }

        myWatchListSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.myWatchListItems)
            .map(MovieModel::class, ThumbViewHolder::class)
            .addViewEventListener(onThumbnailClickListener)
            .create()

        actionMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedActionItems)
            .map(MovieModel::class, ThumbViewHolder::class)
            .addViewEventListener(onThumbnailClickListener)
            .create()

        adventuresMoviesSmartMovieAdapter =
            SmartRecyclerAdapter.items(MovieDataItems.nestedAdventureItems)
                .map(MovieModel::class, ThumbViewHolder::class)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        animatedMoviesSmartMovieAdapter =
            SmartRecyclerAdapter.items(MovieDataItems.nestedAnimatedItems)
                .map(MovieModel::class, ThumbViewHolder::class)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        sciFiMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedSciFiItems)
            .map(MovieModel::class, ThumbViewHolder::class)
            .addViewEventListener(onThumbnailClickListener)
            .create()
    }

    private fun getMovieTitle(smartRecyclerAdapter: SmartRecyclerAdapter, position: Position): String {
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
