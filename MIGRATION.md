# v5.0.0

## SmartViewHolder

#### NEW v5.0.0

Both these works

```kotlin
class PosterViewHolder(parentView: ViewGroup) :
  SmartViewHolder<MovieModel>(parentView, R.layout.poster_item)
```

```kotlin
class PosterViewHolder(parentView: ViewGroup) :
  SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context)
      .inflate(R.layout.poster_item, parentView, false)
  )
```

### OLD 4.1.0 and below

```kotlin
class PosterViewHolder(parentView: ViewGroup) :
  SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context)
      .inflate(R.layout.poster_item, parentView, false)
  )
```

## ViewEvent listeners basics

#### NEW v5.0.0

With smart-recycler-adapter-viewevent extension library

```groovy
dependencies {
  // ViewEvent click listeners, selection, swipe dismiss and drag & drop
  implementation 'io.github.manneohlund:smart-recycler-adapter-viewevent:X.Y.Z'
}
```

```kotlin
.add(OnClickEventListener { event: ViewEvent.OnClick ->
    // Handle event
})
```

#### OLD 4.1.0 and below

```kotlin
.addViewEventListener(onViewEventListener { view, viewEventId, position ->
    // Handle event
})
```

or

```kotlin
.addViewEventListener(object : OnViewEventListener {
  override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
    // Handle event
  }
})
```

### Custom view event listener

#### NEW v5.0.0

```kotlin
.add(OnCustomViewEventListener { event ->
    // Handle event
})
```

```kotlin
open class SimpleCustomViewEventListenerViewHolder(parentView: ViewGroup) :
  SmartViewHolder<Int>(parentView, R.layout.simple_item),
  CustomViewEventListenerHolder,
  SmartAdapterHolder {

  override lateinit var customViewEventListener: (ViewEvent) -> Unit
  override var smartRecyclerAdapter: SmartRecyclerAdapter? = null

  init {
    itemView.setOnClickListener { view ->
      customViewEventListener.invoke(
        ViewEvent.OnClick(smartRecyclerAdapter!!, this, adapterPosition, view)
      )
    }
  }
}
```

#### OLD 4.X.X and below

```kotlin
// SmartRecyclerAdapter
.addViewEventListener(onViewEventListener { view, viewEventId, position ->
    // Handle event
})
```

```kotlin
// ViewHolder
open class SimpleItemViewHolder(parentView: ViewGroup) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context).inflate(R.layout.simple_item, parentView, false)
), ViewEventListenerHolder {

  override lateinit var viewEventListener: OnViewEventListener

  init {
    itemView.setOnClickListener { view ->
      viewEventListener.onViewEvent(view, R.id.action_play_movie, adapterPosition)
    }
  }
}
```

### Drag & Drop

#### NEW v5.0.0 with smart-recycler-adapter-viewevent library

```kotlin
.add(AutoDragAndDropBinder(longPressDragEnabled = true) { event: ViewEvent.OnItemMoved ->
    // Handle drag event
})
```

#### OLD 4.X.X

```kotlin
.addExtensionBuilder(DragAndDropExtensionBuilder(AutoDragAndDropExtension()).apply {
    longPressDragEnabled = true
    onItemMovedListener = { oldViewHolder, targetViewHolder ->
        // Handle drag event
    }
})
```

### Swipe

#### NEW v5.0.0 with smart-recycler-adapter-viewevent library

```kotlin
.add(SwipeRemoveItemBinder(ItemTouchHelper.LEFT) {
    // Remove item from SmartRecyclerAdapter data set
    smartRecyclerAdapter.removeItem(it.viewHolder.adapterPosition)
})
```

#### OLD 4.X.X

```kotlin
.addExtensionBuilder(SwipeExtensionBuilder(SwipeRemoveItemExtension()).apply {
    swipeFlags = ItemTouchHelper.LEFT
    onItemSwipedListener = { viewHolder, direction ->
        showToast(viewHolder, direction)
        // Remove item from SmartRecyclerAdapter data set
        smartRecyclerAdapter.removeItem(viewHolder.adapterPosition)
    }
})
```

## Diff util

#### NEW v5.0.0

With smart-recycler-adapter-diffutil extension library

```groovy
dependencies {
  implementation 'io.github.manneohlund:smart-recycler-adapter-diffutil:X.Y.Z'
}
```

No `DiffUtilExtensionBuilder` needed anymore.

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(Integer::class, SimpleItemViewHolder::class)
  .add(SimpleDiffUtilExtension(predicate))
  .into(recyclerView)
```

#### OLD 4.X.X

```kotlin
val adapter = SmartRecyclerAdapter
  .items(items)
  .map(Integer::class, SimpleItemViewHolder::class)
  .into(recyclerView)

diffUtilExtension = DiffUtilExtensionBuilder().apply {
  smartRecyclerAdapter = adapter
  diffPredicate = predicate
}.build()
```

# 4.X.X & 3.X.X

### ViewEvent Migration

#### OLD before v3.0.0

Variable parameter overloading with many different `addViewEventListener` calls.

```java
.addViewEventListener(
    MovieViewHolder.class,
    R.id.event_on_click,
    (view, viewEventId, position) -> playMovie())
```

```java
class MovieViewHolder
    extends SmartAutoEventViewHolder<MyWatchListModel>
    implements SmartAdapterHolder {}
```

#### NEW in v3.0.0

Create an `OnItemClickListener` for MovieViewHolder.
`SmartAutoEvent` implementations has been removed so no need for ex `MovieViewHolder` to extend `SmartAutoEventViewHolder`.

```java
interface OnMovieItemClickListener extends OnItemClickListener {
  @NonNull
  @Override
  default Class<? extends SmartViewHolder> getViewHolderType() {
    return MovieViewHolder.class;
  }
}
```

Add listener to the SmartAdapterBuilder.

```java
.addViewEventListener((OnMovieItemClickListener) (view, viewEventId, position) -> playMovie())
```


### New in v4.0.0 (Kotlin)

In Kotlin the interface properties are overridden instead of default methods as in java.
`SmartViewHolderType` is a *typealias* of `KClass<out SmartViewHolder<*>>`.

```kotlin
interface OnMovieItemClickListener : OnItemClickListener {
  override val viewHolderType: SmartViewHolderType
    get() = MovieViewHolder::class
}
```

```kotlin
.addViewEventListener(object : OnMovieItemClickListener {
  override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
    playMovie()
  }
})
```

----

Kotlin has no SAM constructor for interface as java so lambda resolving is not working yet.
However you can do lambda call to eg `OnItemClickListener` extensions like this:

```kotlin
inline fun onMovieItemClickListener(crossinline viewEvent: (
        view: View,
        viewEventId: ViewEventId,
        position: Position) -> Unit) = object : OnItemClickListener {
    override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
        viewEvent(view, viewEventId, position)
    }
}
```

```kotlin
.addViewEventListener(onMovieItemClickListener { view, viewEventId, position -> playMovie() })
```