# smart-recycler-adapter

[![Download](https://api.bintray.com/packages/manneohlund/maven/smart-recycler-adapter/images/download.svg)](https://bintray.com/manneohlund/maven/smart-recycler-adapter/_latestVersion)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-SmartRecyclerAdapter-green.svg?style=flat )]( https://android-arsenal.com/details/1/7766)

Never code any boilerplate RecyclerAdapter again!  
This library will make it easy and painless to map your data item with a target ViewHolder.

<p align="center"><img width="240" height="499" src="https://user-images.githubusercontent.com/11292383/60390746-cc723f00-9add-11e9-8695-65315836a6f8.gif"></p>

# Features

###### OnViewEventListener
* Smart <b>OnClick</b> / <b>OnLongClickListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/SimpleItemOnClickOnLongClickActivity.kt" target="_blank">SimpleItemOnClickOnLongClickActivity</a></sup>
* State holding with <b>OnItemSelectedListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
* Custom View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/CustomViewEventActivity.kt" target="_blank">CustomViewEventActivity</a></sup>
###### ItemTouchHelper Swipe, Drag & Drop extensions
* Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/DragAndDropItemActivity.kt" target="_blank">DragAndDropItemActivity</a></sup>
* Drag & drop with handle  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/DragAndDropHandleItemActivity.kt" target="_blank">DragAndDropHandleItemActivity</a></sup>
* Swipe to remove item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/SwipeRemoveItemActivity.kt" target="_blank">SwipeRemoveItemActivity</a></sup>
* Drag & drop, Swipe, View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultipleEventsAndExtensionsActivity.kt" target="_blank">MultipleEventsAndExtensionsActivity</a></sup>
* Grid + Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/GridActivity.kt" target="_blank">GridActivity</a></sup>
###### ViewTypeResolver
* Multiple ViewHolder types resolver  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
###### SmartStateHolder
* Multiple items select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultiSelectItemsActivity.kt" target="_blank">MultiSelectItemsActivity</a></sup>
* Single RadioButton select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/SingleSelectRadioButtonItemActivity.kt" target="_blank">SingleSelectRadioButtonItemActivity</a></sup>
* Multiple CheckBox select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultiSelectCheckBoxItemsActivity.kt" target="_blank">MultiSelectCheckBoxItemsActivity</a></sup>
* Multiple Switch select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultiSelectSwitchItemsActivity.kt" target="_blank">MultiSelectSwitchItemsActivity</a></sup>
* Multiple Expandable items  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/MultipleExpandableItemActivity.kt" target="_blank">MultipleExpandableItemActivity</a></sup>
* Single Expandable item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/SingleExpandableItemActivity.kt" target="_blank">SingleExpandableItemActivity</a></sup>
###### Nested adapter
* Nested SmartRecyclerAdapter  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/NestedSmartRecyclerAdaptersActivity.kt" target="_blank">NestedSmartRecyclerAdaptersActivity</a></sup>
###### Pagination
* Endless scroll  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/EndlessScrollActivity.kt" target="_blank">EndlessScrollActivity</a></sup>
* Endless scroll with load more button  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/EndlessScrollLoadMoreButtonActivity.kt" target="_blank">EndlessScrollLoadMoreButtonActivity</a></sup>
###### DiffUtil
* Diff Util extension  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/3.0.0/sample/src/main/java/smartrecycleradapter/feature/DiffUtilActivity.kt" target="_blank">DiffUtilActivity</a></sup>

#### Release overview

* Kotlin + AndroidX (jcenter, jitpack) [v4.0.0](https://github.com/manneohlund/smart-recycler-adapter/tree/4.0.0)
* Java + AndroidX (jcenter, jitpack) [v3.0.0](https://github.com/manneohlund/smart-recycler-adapter/tree/3.0.0)
* Java + AppCompat (jitpack) [v2.2.0](https://github.com/manneohlund/smart-recycler-adapter/tree/2.2.0)

# Gradle
Add `jcenter()` or `maven { url  "https://dl.bintray.com/manneohlund/maven" }` to your `build.gradle` under `repositories`
```groovy
dependencies {
  implementation 'io.github.manneohlund:smart-recycler-adapter:4.0.0'
}
```

# Basic
### Basic adapter creation

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(MoviePosterModel::class, PosterViewHolder::class)
  .map(MovieBannerModel::class, BannerViewHolder::class)
  .map(MovieModel::class, MovieViewHolder::class)
  .map(TopNewsModel::class, TopNewsViewHolder::class)
  .into<SmartRecyclerAdapter>(recyclerView)
 ```

### SmartViewHolder

Just extend your ViewHolder class with `SmartViewHolder` and pass in the target type ex `SmartViewHolder<Mail>`.  
Note that the constructor can both take `View` or `ViewGroup` as parameter, in this case `PosterViewHolder(ViewGroup parentView)` to avoid casting to ViewGroup while inflating.  
The `parentView` is the recyclerView.<br/>
The method `unbind` has an default implementation and is optional.

##### Works with Android DataBinding! Just add the DataBinding LayoutInflater in `super` call. 🚀

```kotlin
class PosterViewHolder(parentView: ViewGroup) : SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context).inflate(R.layout.poster_item, parentView, false)
) {

  override fun bind(movie: MovieModel) {
    Glide.with(imageView)
      .load(model.posterUrl)
      .into(imageView)
  }
  
  override fun unbind() {
    Glide.with(imageView).clear(imageView)
  }
} 
```

### View Events
  
You can easily assign events to views and add an `OnViewEventListener` to the SmartRecyclerAdapter for easy event handling.

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(MovieModel::class, MovieViewHolder::class)
  // Adds a basic `OnViewEventListener` to any `SmartViewHolder` extension that implements `ViewEventListenerHolder`
  .addViewEventListener(onViewEventListener { view, viewEventId, position -> handleItemEvent()})
  .into<SmartRecyclerAdapter>(recyclerView)
```
 
In your view holder, add eg `OnClickListener` to a view and call `onViewEvent` on the `OnViewEventListener`.<br/>
Your `ViewHolder` must implements `ViewEventListenerHolder` to receive the `OnViewEventListener`. 
  
```kotlin
open class SimpleItemViewHolder(parentView: ViewGroup) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context).inflate(R.layout.simple_item, parentView, false)
), ViewEventListenerHolder {

  private lateinit var viewEventListener: OnViewEventListener

  override fun setOnViewEventListener(viewEventListener: OnViewEventListener) {
    this.viewEventListener = viewEventListener
  }

  init {
    itemView.setOnClickListener { view ->
      viewEventListener.onViewEvent(view, R.id.action_play_movie, adapterPosition)
    }
  }

  override fun bind(item: Int) {
    // Handle binding
  }
}
```

Kotlin has no SAM constructors so instead of writing interface instantiation you can utilize lambda helper methods for all predefined library event listeners ex:

```kotlin
.addViewEventListener(onViewEventListener { view, viewEventId, position ->
  // Handle event
})
```

is same as

```kotlin
.addViewEventListener(object : OnViewEventListener {
  override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
    // Handle event
    }
})
```

#### Predefined Listeners in the lib

If you are lazy and want to auto assign a predefined `onClickListener` and `onLongClickListener` with viewEventIds `R.id.event_on_click` and `R.id.event_on_long_click`,

###### Define listeners for MovieViewHolder

Default implemented view event id for `OnItemClickListener` is `R.id.event_on_click`.
Default implemented view id for `OnItemClickListener` is `R.id.undefined`. 
`R.id.undefined` targets root view of the ViewHolder (ViewHolder.itemView).

```kotlin
interface OnMovieItemClickListener : OnItemClickListener {
  override val viewHolderType: SmartViewHolderType
    get() = MovieViewHolder::class
}
```

SmartRecyclerAdapter will automatically bind an `View.OnClickListener` to a view with id `R.id.movie_info_button`.

```kotlin
interface OnMovieInfoButtonClickListener : OnItemClickListener {
  override val viewHolderType: SmartViewHolderType
    get() = MovieViewHolder::class

  override val viewId: ViewId
    get() = R.id.movie_info_button
}
```

And add event listener to `SmartRecyclerAdapter` builder.

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(HeaderModel::class, HeaderViewHolder::class)
  .map(MovieModel::class, MovieViewHolder::class)
  .map(MovieTrailerModel::class, MovieTrailerViewHolder::class)
  // Adds `OnItemClickListener` and auto binds `View.OnClickListener` on all ViewHolders.
  .addViewEventListener(onItemClickListener { view, viewEventId, position -> handleEvent(viewEventId) })
  // Adds event listener for MovieViewHolder only and overrides any generic `OnItemClickListener`
  .addViewEventListener(object : OnMovieItemClickListener {
    override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
      playMovie()
    }
  })
  // Adds event listener for MovieViewHolder only and auto binds `View.OnClickListener` on view with id `R.id.movie_info_button`
  .addViewEventListener(object : OnMovieInfoButtonClickListener {
    override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
      showMovieInfo(position)
    }
  })
  .into<SmartRecyclerAdapter>(recyclerView)
```

### Adapter creation with ViewTypeResolver
  
If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.  
Note .map() call not needed in this case but you can combine if you want to.
  
```kotlin
SmartRecyclerAdapter
  .items(items)
  .setViewTypeResolver{ item, position -> {
    when { 
      item is MovieTrailerModel -> MovieTrailerViewHolder::class
      item is MovieModel && item.isRatedR() -> RMovieViewHolder::class
      else -> MovieViewHolder::class // Add default view if needed, else SmartRecyclerAdapter will look at the base `.map` mapping
    }
  }}
  .into<SmartRecyclerAdapter>(recyclerView)
```

# New nested SmartRecyclerAdapter from v2.0.0

New in `SmartRecyclerAdapter` v2.0.0 is support for nested recycler adapter.
Now you can easily build complex nested adapters without hustle and have full control of the adapter in your view controlling `Fragment` or `Activity`. 
Use the new `create()` method instead of the `into(recyclerView)` to create just the `SmartRecyclerAdapter` then set the adapter to the recycler view in your `ViewHolder`.
Just implement the `SmartAdapterHolder` interface in your `ViewHolder` and `SmartRecyclerAdapter` will handle the mapping.

### 1. Create your nested SmartRecyclerAdapter

```kotlin
val myWatchListSmartMovieAdapter: SmartRecyclerAdapter = SmartRecyclerAdapter
  .items(myWatchListItems)
  .map(MovieModel::class, ThumbViewHolder::class)
  .addViewEventListener(onItemClickListener { view, viewEventId, position -> playMovie() })
  .create()
````

### 2. Map myWatchListSmartMovieAdapter with MyWatchListViewHolder

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(MoviePosterModel::class, PosterViewHolder::class)
  .map(MyWatchListModel::class, MyWatchListViewHolder::class)
  .map(MyWatchListViewHolder::class, myWatchListSmartMovieAdapter)
  .into<SmartRecyclerAdapter>(recyclerView)
```

### 3. Map myWatchListSmartMovieAdapter to MyWatchListViewHolder

```kotlin
class MyWatchListViewHolder : SmartViewHolder<MyWatchListModel>, SmartAdapterHolder {
    
  override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
    set(value) {
      field = value
      recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, HORIZONTAL, false)
      recyclerView.adapter = value
    }

  override fun bind(myWatchListModel: MyWatchListModel) {
    // bind model data to views
  }
    
  override fun unbind() {
    // optional unbinding of view data model
  }
}
```

### SmartEndlessScrollRecyclerAdapter

A popular feature in apps is to have endless scrolling with pagination, in other words load more items when user has scrolled to bottom.
With SmartEndlessScrollRecyclerAdapter you can achieve this.

#### 1. Create adapter
```kotlin
val endlessScrollAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter
  .items(items)
  .map(MovieModel::class, MovieViewHolder::class)
  .into(recyclerView)
```

#### 2. Set OnLoadMoreListener to your SmartEndlessScrollRecyclerAdapter

Called when scrolled to the last item and loading view is visible.

```kotlin
endlessScrollAdapter.setOnLoadMoreListener{
  endlessScrollAdapter.addItems(moreItems)
}
```

#### More SmartEndlessScrollRecyclerAdapter features

Enable/Disable endless scrolling and thus removing the loading view.
`endlessScrollAdapter.isEndlessScrollEnabled = false`

You can also set your custom loading/loadmore view.
`endlessScrollAdapter.setCustomLoadMoreLayoutResource(R.layout.your_custom_loadmore_view);`

# More

For more samples test out the sample app and see the [source code](https://github.com/manneohlund/smart-recycler-adapter/tree/master/sample/src/main/java/com/example/smartrecycleradapter).

### RecyclableViewHolder

Sometimes a ViewHolder created by the Adapter cannot be recycled due to its transient state.
In order to fix this is to implement `RecyclableViewHolder` in your `SmartViewHolder` extension so that upon receiving this callback, 
Adapter can clear the animation(s) that effect the View's transient state and return <code>true</code> so that the View can be recycled.

```kotlin
class MovieViewHolder : SmartViewHolder, RecyclableViewHolder {
  override fun onFailedToRecycleView(): Boolean = true
}
```

### OnViewAttachedToWindowListener and OnViewDetachedFromWindowListener

If you want to catch when the view is attached and detached from the window in your ViewHolder you can implement `OnViewAttachedToWindowListener` and `OnViewDetachedFromWindowListener` in your `SmartViewHolder` extension.
Becoming detached from the window is not necessarily a permanent condition the consumer of an Adapter's views may choose to cache views offscreen while they are not visible, attaching and detaching them as appropriate.

```kotlin
class MovieViewHolder : SmartViewHolder, 
    OnViewAttachedToWindowListener, 
    OnViewDetachedFromWindowListener { 

  override fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) {
    // Restore
  }

  override fun onViewDetachedFromWindow(viewHolder: RecyclerView.ViewHolder) {
    // Cache
  }
}
```

# Migrations

More guides coming to the [Wiki Page](https://github.com/manneohlund/smart-recycler-adapter/wiki)

### ViewEvent Migration

#### Old way before v3.0.0

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

#### New in v3.0.0

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

### More SmartRecyclerAdapter features

```kotlin
val adapter: SmartRecyclerAdapter = SmartRecyclerAdapter
    .items(items)
    .map(MovieModel::class, MovieViewHolder::class)
    .into(recyclerView)

// We can add more data
adapter.addItems(items)

// Add data at index with animation
adapter.addItem(0, item)

// Add data at index without animation
adapter.addItem(0, item, false)

// Remove item at index with animation
adapter.removeItem(0)

// Remove item at index without animation
adapter.removeItem(0, false)

// Replace item at index with animation
adapter.replaceItem(0, item)

// Replace item at index without animation
adapter.replaceItem(0, item, false)

// Get items by type
adapter.getItems(MovieModel::class)

// Delete all items in the list
adapter.clear()
```
