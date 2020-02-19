package smartrecycleradapter

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_movie_category_details.*
import smartadapter.Position
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.SmartRecyclerAdapter
import smartrecycleradapter.data.MovieDataItems
import smartrecycleradapter.extension.GridAutoLayoutManager
import smartrecycleradapter.models.MovieModel
import smartrecycleradapter.viewholder.HeaderViewHolder
import smartrecycleradapter.viewholder.ThumbViewHolder

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

        val adapterItems = mutableListOf(
            movieType.title,
            *movieItems.toTypedArray()
        )

        val gridAutoLayoutManager = GridAutoLayoutManager(this, 100)
        gridAutoLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Position): Int {
                return if (position == 0) gridAutoLayoutManager.spanCount else 1
            }
        }

        when (movieType) {
            MovieType.COMING_SOON, MovieType.MY_WATCH_LIST -> {
                SmartRecyclerAdapter.items(adapterItems)
                        .map(String::class, HeaderViewHolder::class)
                        .map(MovieModel::class, ThumbViewHolder::class)
                        .setLayoutManager(gridAutoLayoutManager)
                        .addViewEventListener(ThumbViewHolder.OnItemClickListener { view, smartRecyclerAdapter, position ->
                            Toast.makeText(applicationContext, "Movie $position", Toast.LENGTH_SHORT).show()
                        })
                        .into<SmartRecyclerAdapter>(recyclerView)
            }
            MovieType.ACTION, MovieType.ADVENTURE, MovieType.ANIMATED, MovieType.SCI_FI -> {
                val endlessScrollAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.items(adapterItems)
                        .map(String::class, HeaderViewHolder::class)
                        .map(MovieModel::class, ThumbViewHolder::class)
                        .setLayoutManager(gridAutoLayoutManager)
                        .addViewEventListener(ThumbViewHolder.OnItemClickListener { view, smartRecyclerAdapter, position ->
                            Toast.makeText(applicationContext, "Movie $position", Toast.LENGTH_SHORT).show()
                        })
                        .into(recyclerView)

                endlessScrollAdapter.autoLoadMoreEnabled = true
                endlessScrollAdapter.onLoadMoreListener = {
                    if (!endlessScrollAdapter.isLoading) {
                        endlessScrollAdapter.isLoading = true

                        Handler().postDelayed({
                            endlessScrollAdapter.addItems(movieItems)
                            if (endlessScrollCount++ == 3) {
                                endlessScrollAdapter.isEndlessScrollEnabled = false
                            }
                            endlessScrollAdapter.isLoading = false
                        },
                            3000)
                    }
                }
            }
        }
    }
}
