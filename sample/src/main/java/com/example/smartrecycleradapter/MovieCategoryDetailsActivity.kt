package com.example.smartrecycleradapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.example.smartrecycleradapter.data.MovieDataItems
import com.example.smartrecycleradapter.extension.GridAutoLayoutManager
import com.example.smartrecycleradapter.models.MovieModel
import com.example.smartrecycleradapter.viewholder.HeaderViewHolder
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder
import kotlinx.android.synthetic.main.activity_movie_category_details.*
import smartadapter.SmartRecyclerAdapter
import java.util.Arrays.asList

enum class MovieType(val title: String) {
    COMING_SOON("Coming soon"),
    MY_WATCH_LIST("My watch list"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATED("Animated"),
    SCI_FI("Sci-fi")
}

class MovieCategoryDetailsActivity : AppCompatActivity() {

    private lateinit var movieType: MovieType;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_category_details)

        intent?.getIntExtra("MovieType", MovieType.ANIMATED.ordinal)?.let {
            movieType = MovieType.values()[it];
        }

        initSmartRecyclerAdapter()
    }

    private fun initSmartRecyclerAdapter() {
        val movieItems: List<Any> = when (movieType) {
            MovieType.COMING_SOON -> MovieDataItems.comingSoonItems
            MovieType.MY_WATCH_LIST -> MovieDataItems.myWatchListItems
            MovieType.ACTION -> MovieDataItems.nestedActionItems
            MovieType.ADVENTURE -> MovieDataItems.nestedAdventureItems
            MovieType.ANIMATED -> MovieDataItems.nestedAnimatedItems
            MovieType.SCI_FI -> MovieDataItems.nestedSciFiItems
            else -> asList()
        }

        val adapterItems: ArrayList<Any> = ArrayList<Any>()
        adapterItems.add(movieType.title);
        adapterItems.addAll(movieItems)

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) gridAutoLayoutManager.spanCount else 1
            }
        }

        SmartRecyclerAdapter.items(adapterItems)
                .map(String::class.java, HeaderViewHolder::class.java)
                .map(MovieModel::class.java, ThumbViewHolder::class.java)
                .setLayoutManager(gridAutoLayoutManager)
                .addViewEventListener(ThumbViewHolder::class.java, R.id.action_on_click) { view, actionId, position ->
                    Toast.makeText(this, "Movie $position", Toast.LENGTH_SHORT).show()
                }
                .into(recyclerView)
    }
}
