package com.example.smartrecycleradapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.example.smartrecycleradapter.data.MoviePosterItems.*
import com.example.smartrecycleradapter.extension.GridAutoLayoutManager
import com.example.smartrecycleradapter.models.MovieViewModel
import com.example.smartrecycleradapter.viewholder.HeaderViewHolder
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder
import kotlinx.android.synthetic.main.activity_movie_category_details.*
import smartadapter.SmartRecyclerAdapter
import java.util.*

enum class MovieType(val title: String) {
    COMING_SOON("Coming soon"),
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
        val items = when (movieType) {
            MovieType.COMING_SOON -> COMING_SOON_ITEMS
            MovieType.ACTION -> ACTION_ITEMS
            MovieType.ADVENTURE -> ADVENTURE_ITEMS
            MovieType.ANIMATED -> ANIM_ITEMS
            MovieType.SCI_FI -> SCI_FI_ITEMS
            else -> intArrayOf()
        }

        val movieItems = ArrayList<Any>()
        movieItems.add(movieType.title)

        for (poster in items) {
            movieItems.add(MovieViewModel(poster))
        }

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) gridAutoLayoutManager.spanCount else 1
            }
        }

        SmartRecyclerAdapter.items(movieItems)
                .map(String::class.java, HeaderViewHolder::class.java)
                .map(MovieViewModel::class.java, ThumbViewHolder::class.java)
                .setLayoutManager(gridAutoLayoutManager)
                .addViewEventListener(ThumbViewHolder::class.java, R.id.action_on_click) { view, actionId, position ->
                    Toast.makeText(this, "Movie $position", Toast.LENGTH_SHORT).show()
                }
                .into(recyclerView)
    }
}
