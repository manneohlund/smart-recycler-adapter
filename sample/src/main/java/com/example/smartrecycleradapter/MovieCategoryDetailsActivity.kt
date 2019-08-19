package com.example.smartrecycleradapter

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartrecycleradapter.data.MovieDataItems
import com.example.smartrecycleradapter.extension.GridAutoLayoutManager
import com.example.smartrecycleradapter.models.MovieModel
import com.example.smartrecycleradapter.viewholder.HeaderViewHolder
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder
import kotlinx.android.synthetic.main.activity_movie_category_details.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.SmartRecyclerAdapter

enum class MovieType(val title: String) {
    COMING_SOON("Coming soon"),
    MY_WATCH_LIST("My watch list"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATED("Animated"),
    SCI_FI("Sci-fi")
}

class MovieCategoryDetailsActivity : AppCompatActivity() {

    private lateinit var movieType: MovieType
    private var endlessScrollCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_category_details)

        intent?.getIntExtra("MovieType", MovieType.ANIMATED.ordinal)?.let {
            movieType = MovieType.values()[it]
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
        }

        val adapterItems: ArrayList<Any> = ArrayList()
        adapterItems.add(movieType.title)
        adapterItems.addAll(movieItems)

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) gridAutoLayoutManager.spanCount else 1
            }
        }

        when (movieType) {
            MovieType.COMING_SOON, MovieType.MY_WATCH_LIST -> {
                SmartRecyclerAdapter.items(adapterItems)
                        .map(String::class.java, HeaderViewHolder::class.java)
                        .map(MovieModel::class.java, ThumbViewHolder::class.java)
                        .setLayoutManager(gridAutoLayoutManager)
                        .addViewEventListener(object : ThumbViewHolder.OnItemClickListener {
                            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                                Toast.makeText(applicationContext, "Movie $position", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .into(recyclerView)
            }
            MovieType.ACTION, MovieType.ADVENTURE,MovieType.ANIMATED, MovieType.SCI_FI -> {
                val endlessScrollAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.items(adapterItems)
                        .map(String::class.java, HeaderViewHolder::class.java)
                        .map(MovieModel::class.java, ThumbViewHolder::class.java)
                        .setLayoutManager(gridAutoLayoutManager)
                        .addViewEventListener(object : ThumbViewHolder.OnItemClickListener {
                            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                                Toast.makeText(applicationContext, "Movie $position", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .into(recyclerView)

                endlessScrollAdapter.setOnLoadMoreListener {
                    if (!endlessScrollAdapter.isLoading) {
                        endlessScrollAdapter.setIsLoading(true)
                        Toast.makeText(applicationContext, "LoadMore", Toast.LENGTH_SHORT).show()

                        Handler().postDelayed({
                            endlessScrollAdapter.addItems(movieItems)
                            if (endlessScrollCount++ == 3) {
                                endlessScrollAdapter.setEndlessScrollEnabled(false);
                            }
                            endlessScrollAdapter.setIsLoading(false)
                        },
                                3000)
                    }
                }
            }
        }
    }
}
