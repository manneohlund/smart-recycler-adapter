
# smart-recycler-adapter-nestedadapter

Creating a complex multi nested SmartRecyclerAdapter have never been easier.<br/>
Supports both dynamic and static adapter creation and view holder mapping.<br/>
Use `smart-recycler-adapter-nestedadapter` with minimum `smart-recycler-adapter:5.0.0`.

### Models

```kotlin
data class MovieData(
	val categories: MutableList<MovieCategory>
)
```

```kotlin
open class MovieCategory(
    val id: String,
    val title: String,
    val type: String,
    override var items: MutableList<MovieModel>
) : SmartNestedItem<MovieModel> {

    override fun toString(): String {
        return title
    }
}
```

```kotlin
class MovieModel(
    val title: String,
    internal val icon: String,
    val size: Int = MEDIUM
) {
    open val iconUrl: String
        get() = when (size) {
            POSTER -> "$POSTER_BASE_URL$icon.jpg"
            BANNER -> "$BANNER_BASE_URL$icon.jpg"
            else -> "$THUMBS_BASE_URL$icon.jpg"
        }

    companion object {
        const val POSTER = 4
        const val BANNER = 3
        const val LARGE = 2
        const val MEDIUM = 1
        const val SMALL = 0
    }
}
```

### SmartViewHolder

Sample uses kotlin synthetic view property import!

```kotlin
class NestedRecyclerViewHolder(parentView: ViewGroup) :
    SmartViewHolder<MovieCategory>(parentView, R.layout.nested_recycler_view),
    SmartNestedRecyclerViewHolder {

    override val recyclerView: RecyclerView = itemView.nestedRecyclerView

    init {
        itemView.nestedRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    override fun bind(item: MovieCategory) {
        itemView.title.text = item.title
    }
}
```

### SmartRecyclerAdapter

```kotlin
SmartRecyclerAdapter
    .items(items)
    .map(CopyrightModel::class, CopyrightViewHolder::class)
    .setViewTypeResolver { item, position ->
        // Dynamically resolve target SmartViewHolder
    }
    .add(
        SmartNestedAdapterBinder(
            viewHolderType = NestedRecyclerViewHolder::class,
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
                    // Handle click event
                })
        )
    )
    .add(OnClickEventListener(NestedRecyclerViewHolder::class, R.id.more) {
        // Handle click event
    })
    .into<SmartRecyclerAdapter>(recyclerView)
```

# That's it! 🚀

## Advanced

### RecyclerViewBinder

With RecyclerViewBinder you don't have to set RecycleView properties in your ViewHolder.<br/>
SmartNestedAdapterBinder will handle that for you and you can specify the mapping in the SmartAdapterBuilder.

```kotlin
SmartNestedAdapterBinder(
    viewHolderType = NestedRecyclerViewHolder::class,
    smartRecyclerAdapterBuilder = SmartRecyclerAdapter.empty()
        .setViewTypeResolver(thumbViewHolderResolver)
        .add(OnClickEventListener {
            showToast(
                "${it.adapter::class.java.simpleName}\n%s \nindex %d",
                getMovieTitle(it.adapter, it.position),
                it.position
            )
        }),
    recyclerViewBinder = { viewHolder, recyclerView ->
        when (viewHolder) {
            is MyWatchListViewHolder -> recyclerView.layoutManager = GridAutoLayoutManager(this, 100)
            is RecentlyPlayedMoviesViewHolder -> recyclerView.layoutManager = GridAutoLayoutManager(this, 60)
            else -> recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false).apply {
                isItemPrefetchEnabled = true
                initialPrefetchItemCount = 20
            }
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
    }
)
```

### SmartNestedAdapterBinder

SmartNestedAdapterBinder binding mechanism is happening in OnBindViewHolderListener.onBindViewHolder.

```kotlin
override fun onBindViewHolder(
    adapter: SmartRecyclerAdapter,
    viewHolder: SmartViewHolder<Any>
) {
    val items = (adapter.getItem(viewHolder.adapterPosition) as SmartNestedItem<*>).items
    (viewHolder as SmartNestedRecyclerViewHolder).recyclerView.adapter =
        nestedAdapters[viewHolder]!!.also {
            it.setItems(items as MutableList<*>)
        }
}
```