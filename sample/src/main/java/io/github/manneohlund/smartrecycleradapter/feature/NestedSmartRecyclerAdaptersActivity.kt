package io.github.manneohlund.smartrecycleradapter.feature

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import io.github.manneohlund.smartrecycleradapter.BuildConfig
import io.github.manneohlund.smartrecycleradapter.DemoActivity.getActionName
import io.github.manneohlund.smartrecycleradapter.R
import io.github.manneohlund.smartrecycleradapter.data.MovieDataItems
import io.github.manneohlund.smartrecycleradapter.models.*
import io.github.manneohlund.smartrecycleradapter.viewholder.*
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.SmartRecyclerAdapter
import java.util.*


/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class NestedSmartRecyclerAdaptersActivity : BaseSampleActivity() {

    lateinit var comingSoonSmartMovieAdapter: SmartEndlessScrollRecyclerAdapter
    lateinit var myWatchListSmartMovieAdapter: SmartRecyclerAdapter
    lateinit var actionMoviesSmartMovieAdapter: SmartRecyclerAdapter
    lateinit var adventuresMoviesSmartMovieAdapter: SmartRecyclerAdapter
    lateinit var animatedMoviesSmartMovieAdapter: SmartRecyclerAdapter
    lateinit var sciFiMoviesSmartMovieAdapter: SmartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Nested SmartRecyclerAdapters";

        initNestedSmartRecyclerAdapters()
        initSmartRecyclerAdapter()
    }

    private fun initSmartRecyclerAdapter() {
        val items = listOf(
                ComingSoonMoviesModel("Coming soon"),
                MyWatchListModel("My watch list"),
                ActionMoviesModel("Action"),
                AdventureMoviesModel("Adventure"),
                AnimatedMoviesModel("Animated"),
                SciFiMoviesModel("Sci-Fi"),
                CopyrightModel(String.format("SmartRecyclerAdapter v%s\n\nDeveloped by Manne Öhlund", BuildConfig.VERSION_NAME))
        )

        SmartRecyclerAdapter
                .items(items)
                .map(MoviePosterModel::class.java, PosterViewHolder::class.java)
                .map(ComingSoonMoviesModel::class.java, ComingSoonMoviesViewHolder::class.java)
                .map(MyWatchListModel::class.java, MyWatchListViewHolder::class.java)
                .map(ActionMoviesModel::class.java, ActionMoviesViewHolder::class.java)
                .map(AdventureMoviesModel::class.java, AdventureMoviesViewHolder::class.java)
                .map(AnimatedMoviesModel::class.java, AnimatedMoviesViewHolder::class.java)
                .map(SciFiMoviesModel::class.java, SciFiMoviesViewHolder::class.java)
                .map(RecentlyPlayedMoviesModel::class.java, RecentlyPlayedMoviesViewHolder::class.java)
                .map(CopyrightModel::class.java, CopyrightViewHolder::class.java)

                // Map nested SmartRecyclerAdapter
                .map(ComingSoonMoviesViewHolder::class.java, comingSoonSmartMovieAdapter)
                .map(MyWatchListViewHolder::class.java, myWatchListSmartMovieAdapter)
                .map(ActionMoviesViewHolder::class.java, actionMoviesSmartMovieAdapter)
                .map(AdventureMoviesViewHolder::class.java, adventuresMoviesSmartMovieAdapter)
                .map(AnimatedMoviesViewHolder::class.java, animatedMoviesSmartMovieAdapter)
                .map(SciFiMoviesViewHolder::class.java, sciFiMoviesSmartMovieAdapter)

                .into<SmartRecyclerAdapter>(recyclerView)
    }

    var moreItemsLoadedCount = 0
    private fun initNestedSmartRecyclerAdapters() {
        val onThumbnailClickListener = object : ThumbViewHolder.OnItemClickListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                showToast("Coming soon \n%s \n%s index: %d", getMovieTitle(comingSoonSmartMovieAdapter, position), getActionName(actionId), position)
            }
        }

        comingSoonSmartMovieAdapter = SmartEndlessScrollRecyclerAdapter.items(MovieDataItems.comingSoonItems)
                .map(MovieModel::class.java, LargeThumbViewHolder::class.java)
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
            },
                    1000)
        }

        myWatchListSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.myWatchListItems)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        actionMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedActionItems)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        adventuresMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedAdventureItems)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        animatedMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedAnimatedItems)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .addViewEventListener(onThumbnailClickListener)
                .create()

        sciFiMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.nestedSciFiItems)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .addViewEventListener(onThumbnailClickListener)
                .create()
    }

    private fun getMovieTitle(smartRecyclerAdapter: SmartRecyclerAdapter, position: Int): String {
        val movieModel = smartRecyclerAdapter.getItem(position) as MovieModel
        return movieModel.title
    }

    fun showToast(format: String, vararg args: Any) {
        Toast.makeText(
                this,
                String.format(Locale.ENGLISH, format, *args),
                Toast.LENGTH_LONG).show()
    }
}