
# smart-recycler-adapter

[![Download](https://api.bintray.com/packages/manneohlund/maven/smart-recycler-adapter/images/download.svg)](https://bintray.com/manneohlund/maven/smart-recycler-adapter/_latestVersion)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-SmartRecyclerAdapter-green.svg?style=flat )]( https://android-arsenal.com/details/1/7766)
[![Build Status](https://travis-ci.com/manneohlund/smart-recycler-adapter.svg?branch=master)](https://travis-ci.com/manneohlund/smart-recycler-adapter)

Never code any boilerplate RecyclerAdapter again!
This library will make it easy and painless to map your data item with a target ViewHolder.

<p align="center"><img width="240" height="499" src="https://user-images.githubusercontent.com/11292383/60390746-cc723f00-9add-11e9-8695-65315836a6f8.gif"></p>

# Features

###### OnViewEventListener
* Smart <b>OnClick</b> / <b>OnLongClickListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/SimpleItemOnClickOnLongClickActivity.kt" target="_blank">SimpleItemOnClickOnLongClickActivity</a></sup>
* State holding with <b>OnItemSelectedListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
* Custom View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/CustomViewEventActivity.kt" target="_blank">CustomViewEventActivity</a></sup>
###### ItemTouchHelper Swipe, Drag & Drop extensions
* Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/DragAndDropItemActivity.kt" target="_blank">DragAndDropItemActivity</a></sup>
* Drag & drop with handle  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/DragAndDropHandleItemActivity.kt" target="_blank">DragAndDropHandleItemActivity</a></sup>
* Swipe to remove item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/SwipeRemoveItemActivity.kt" target="_blank">SwipeRemoveItemActivity</a></sup>
* Drag & drop, Swipe, View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultipleEventsAndExtensionsActivity.kt" target="_blank">MultipleEventsAndExtensionsActivity</a></sup>
* Grid + Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/GridActivity.kt" target="_blank">GridActivity</a></sup>
###### ViewTypeResolver
* Multiple ViewHolder types resolver  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
###### SmartStateHolder
* Multiple items select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultiSelectItemsActivity.kt" target="_blank">MultiSelectItemsActivity</a></sup>
* Single RadioButton select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/SingleSelectRadioButtonItemActivity.kt" target="_blank">SingleSelectRadioButtonItemActivity</a></sup>
* Multiple CheckBox select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultiSelectCheckBoxItemsActivity.kt" target="_blank">MultiSelectCheckBoxItemsActivity</a></sup>
* Multiple Switch select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultiSelectSwitchItemsActivity.kt" target="_blank">MultiSelectSwitchItemsActivity</a></sup>
* Multiple Expandable items  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/MultipleExpandableItemActivity.kt" target="_blank">MultipleExpandableItemActivity</a></sup>
* Single Expandable item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/SingleExpandableItemActivity.kt" target="_blank">SingleExpandableItemActivity</a></sup>
###### Nested adapter
* Nested SmartRecyclerAdapter  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/NestedSmartRecyclerAdaptersActivity.kt" target="_blank">NestedSmartRecyclerAdaptersActivity</a></sup>
###### Pagination
* Endless scroll  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/EndlessScrollActivity.kt" target="_blank">EndlessScrollActivity</a></sup>
* Endless scroll with load more button  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/EndlessScrollLoadMoreButtonActivity.kt" target="_blank">EndlessScrollLoadMoreButtonActivity</a></sup>
###### DiffUtil
* Diff Util extension  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/5.0.0-beta01/sample/src/main/kotlin/smartrecycleradapter/feature/DiffUtilActivity.kt" target="_blank">DiffUtilActivity</a></sup>

#### Release overview

* Kotlin + AndroidX (jcenter, jitpack) [v5.0.0-beta01](https://github.com/manneohlund/smart-recycler-adapter/tree/5.0.0-beta01)
* Kotlin + AndroidX (jcenter, jitpack) [v4.0.0](https://github.com/manneohlund/smart-recycler-adapter/tree/4.0.0)
* Java + AndroidX (jcenter, jitpack) [v3.0.0](https://github.com/manneohlund/smart-recycler-adapter/tree/3.0.0)
* Java + AppCompat (jitpack) [v2.2.0](https://github.com/manneohlund/smart-recycler-adapter/tree/2.2.0)

# Gradle
Add `jcenter()` or `maven { url  "https://dl.bintray.com/manneohlund/maven" }` to your `build.gradle` under `repositories`

**Core**
```groovy
dependencies {
  // Core SmartRecyclerAdapter
  implementation 'io.github.manneohlund:smart-recycler-adapter:5.0.0-beta01'
}
```

**Extensions**

```groovy
dependencies {
  // ViewEvent click listeners, multi select, swipe dismiss and drag & drop
  implementation 'io.github.manneohlund:smart-recycler-adapter-viewevent:1.0.0-alpha01'
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
  .add(OnClickEventListener { event: ViewEvent.OnClick -> 
    // Handle event
  })
  .into<SmartRecyclerAdapter>(recyclerView)
 ```

### SmartViewHolder

Just extend your ViewHolder class with `SmartViewHolder` and pass in the target type ex `SmartViewHolder<Mail>`.<br/>
Note that the constructor can both take `View` or `ViewGroup` as parameter, in this case `PosterViewHolder(parentView: ViewGroup)` to avoid casting to ViewGroup while inflating.<br/>
The `parentView` is the recyclerView.<br/>
The method `unbind` has an default implementation and is optional.

```kotlin
class PosterViewHolder(parentView: ViewGroup) : 
  SmartViewHolder<MovieModel>(parentView, R.layout.poster_item) {

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

Works with Android DataBinding! Just add the DataBinding LayoutInflater in `super` call. 🚀

```kotlin
class PosterViewHolder(parentView: ViewGroup) : 
  SmartViewHolder<MovieModel>(
    LayoutInflater.from(parentView.context)
      .inflate(R.layout.poster_item, parentView, false)
  )
```

# smart-recycler-adapter-viewevent

As of `smart-recycler-adapter:v5.0.0` all ViewEvent listeners have been removed from `SmartRecyclerAdapter` and added in this extension library `smart-recycler-adapter-viewevent`.
Essentially the `SmartRecyclerAdapter` will now hold a list of `SmartViewHolderBinder` that can implement any of these interfaces to listen to the adapter view holder stages:
* `OnSmartRecycleAdapterCreatedListener` Invoked from `SmartRecyclerAdapter` init
* `OnCreateViewHolderListener` Invoked from `SmartRecyclerAdapter.onCreateViewHolder`
* `OnBindViewHolderListener` Invoked from `SmartRecyclerAdapter.onBindViewHolder`
* `OnViewAttachedToWindowListener` Invoked from `SmartRecyclerAdapter.onViewAttachedToWindow`
* `OnViewDetachedFromWindowListener` Invoked from `SmartRecyclerAdapter.onViewDetachedFromWindow`

This way all extension libraries has full control over the view holder lifecycle stages and can be hooked with various listeners and state holders.<br/>
You can create any type of `SmartViewHolderBinder` extension and implement any number of the listed adapter listeners.

### View Events

`io.github.manneohlund:smart-recycler-adapter-viewevent:1.0.0-alpha01` comes with a range of ViewEvent listeners.<br/>
Default `viewId` is `R.id.undefined` that targets root view of the ViewHolder (ViewHolder.itemView).

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(MovieModel::class, MovieViewHolder::class)
  // Your ViewHolder must implement CustomViewEventListenerHolder & SmartAdapterHolder
  .add(OnCustomViewEventListener { event: ViewEvent -> })
  // Adds click event listener to all SmartViewHolder root itemView
  .add(OnClickEventListener { event: ViewEvent.OnClick -> })
  // Adds long click event listener to all SmartViewHolder root itemView
  .add(OnLongClickEventListener { event: ViewEvent.OnLongClick -> })
  // Adds click event listener to PosterViewHolder root itemView
  .add(OnClickEventListener(PosterViewHolder::class) { event: ViewEvent.OnClick -> })
  // Adds click event listener to PosterViewHolder on view with id R.id.playButton
  .add(OnClickEventListener(PosterViewHolder::class, R.id.playButton){ event: ViewEvent.OnClick -> })
  // Adds touch event listener to PosterViewHolder
  .add(OnTouchEventListener(PosterViewHolder::class) { event: ViewEvent.OnTouchEvent ->
    when(it.event.action) {
      MotionEvent.ACTION_UP -> // Handle touch event
    }
  })
  .into(recyclerView)
```

### SmartStateHolder & ViewEventViewModel

With `OnMultiItemSelectListener`, `OnMultiItemCheckListener`, `OnSingleItemSelectListener` & `OnSingleItemCheckListener`
you can easily keep track on selection states.

In combination with `ViewEventViewModel` you can keep selection states during screen rotation within the Activity lifecycle.<br/>
`ViewEventViewModel` provides a live data for the selection events.

##### OnMultiItemSelectListener

OnMultiItemSelectListener holds multi select states for recycler adapter positions and takes 4 arguments:<br/>
* If `enableOnLongClick` is true multi select will be enabled after a long click, otherwise a regular `ViewEvent.OnClick` will be emitted when tapping.
* `viewId` is by default `R.id.undefined to target all SmartViewHolder.itemView`.
* `viewHolderType` is by default `SmartViewHolder`::class to target all view holders.
* `eventListener` is by default noop in case of `OnMultiItemSelectListener` will be used with `ViewEventViewModel` along with live data observer.

```kotlin
// Define your ViewEventViewModel for OnMultiItemSelectListener to preserve state.
class MultiItemSelectViewModel :
  ViewEventViewModel<ViewEvent, OnMultiItemSelectListener>(
    OnMultiItemSelectListener(
      enableOnLongClick = true,
    )
)

// Get MultiItemSelectViewModel by androidx default viewModels provider.
private val multiItemSelectViewModel: MultiItemSelectViewModel by viewModels()

// Observe ViewEvent live data.
SmartRecyclerAdapter
  .items(items)
  .map(Integer::class, SimpleSelectableItemViewHolder::class)
  .add(multiItemSelectViewModel.observe(this) { event: ViewEvent ->
    // Either ViewEvent.OnClick or ViewEvent.OnItemSelected when enableOnLongClick = true
  })
  .into(recyclerView)
```

**See sample app section:** [#SmartStateHolder](#smartstateholder)

### Drag & Drop

`AutoDragAndDropBinder` will be activated on long press if longPressDragEnabled = true 
and on release the `AutoDragAndDropBinder` will automatically notify the `SmartRecyclerAdapter` about the item move.<br/>
You can extend the `BasicDragAndDropBinder` or `DragAndDropEventBinder` and create your custom implementation.

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(Integer::class, SimpleItemViewHolder::class)
  .add(AutoDragAndDropBinder(longPressDragEnabled = true) { event: ViewEvent.OnItemMoved ->
    // Handle drag event
  })
  .into(recyclerView)
```

**See sample app section:** [#SmartStateHolder](#smartstateholder)

### Swipe dismiss

`AutoRemoveItemSwipeEventBinder` will automatically remove the item from the adapter on swipe.<br/>
You can extend the `BasicSwipeEventBinder` or `SwipeEventBinder.kt` and create your custom implementation.

```kotlin
SmartRecyclerAdapter
  .items(items)
  .map(Integer::class, SimpleItemViewHolder::class)
  .add(AutoRemoveItemSwipeEventBinder(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) { event: ViewEvent.OnItemSwiped ->
    // Handle swipe event
  })
  .into(recyclerView)
```

**See sample app section:** [#SmartStateHolder](#smartstateholder)

### Adapter creation with ViewTypeResolver

If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.<br/>
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
  .into(recyclerView)
```

# Nested SmartRecyclerAdapter

New in `SmartRecyclerAdapter` v2.0.0 is support for statically resolved nested recycler adapter.
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
  .into(recyclerView)
```

### 3. Map myWatchListSmartMovieAdapter to MyWatchListViewHolder

```kotlin
class MyWatchListViewHolder :
  SmartViewHolder<MyWatchListModel>,
  SmartAdapterHolder {
  
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

For more samples test out the sample app and see the [source code](https://github.com/manneohlund/smart-recycler-adapter/tree/master/sample/src/main/kotlin/com/example/smartrecycleradapter).

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