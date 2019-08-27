# smart-recycler-adapter

[![Release](https://jitpack.io/v/manneohlund/smart-recycler-adapter.svg)](https://jitpack.io/#manneohlund/smart-recycler-adapter)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-SmartRecyclerAdapter-green.svg?style=flat )]( https://android-arsenal.com/details/1/7766)

Never code any boilerplate RecyclerAdapter again!  
This library will make it easy and painless to map your data item with a target ViewHolder.

<p align="center"><img width="240" height="499" src="https://user-images.githubusercontent.com/11292383/60390746-cc723f00-9add-11e9-8695-65315836a6f8.gif"></p>

# Features

* Smart <b>OnClick</b> / <b>OnLongClickListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/SimpleItemOnClickOnLongClickActivity.kt" target="_blank">SimpleItemOnClickOnLongClickActivity</a></sup>
* State holding with <b>OnItemSelectedListener</b>  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
* Custom View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/CustomViewEventActivity.kt" target="_blank">CustomViewEventActivity</a></sup>
* Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/DragAndDropItemActivity.kt" target="_blank">DragAndDropItemActivity</a></sup>
* Drag & drop with handle  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/DragAndDropHandleItemActivity.kt" target="_blank">DragAndDropHandleItemActivity</a></sup>
* Swipe to remove item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/SwipeRemoveItemActivity.kt" target="_blank">SwipeRemoveItemActivity</a></sup>
* Drag & drop, Swipe, View Events  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultipleEventsAndExtensionsActivity.kt" target="_blank">MultipleEventsAndExtensionsActivity</a></sup>
* Grid + Drag & drop  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/GridActivity.kt" target="_blank">GridActivity</a></sup>
* Multiple ViewHolder types resolver  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultipleViewTypesResolverActivity.kt" target="_blank">MultipleViewTypesResolverActivity</a></sup>
* Multiple items select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultiSelectItemsActivity.kt" target="_blank">MultiSelectItemsActivity</a></sup>
* Single RadioButton select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/SingleSelectRadioButtonItemActivity.kt" target="_blank">SingleSelectRadioButtonItemActivity</a></sup>
* Multiple CheckBox select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultiSelectCheckBoxItemsActivity.kt" target="_blank">MultiSelectCheckBoxItemsActivity</a></sup>
* Multiple Switch select  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultiSelectSwitchItemsActivity.kt" target="_blank">MultiSelectSwitchItemsActivity</a></sup>
* Multiple Expandable items  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/MultipleExpandableItemActivity.kt" target="_blank">MultipleExpandableItemActivity</a></sup>
* Single Expandable item  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/SingleExpandableItemActivity.kt" target="_blank">SingleExpandableItemActivity</a></sup>
* Nested SmartRecyclerAdapter  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/NestedSmartRecyclerAdaptersActivity.kt" target="_blank">NestedSmartRecyclerAdaptersActivity</a></sup>
* Endless scroll  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/EndlessScrollActivity.kt" target="_blank">EndlessScrollActivity</a></sup>
* Endless scroll with load more button  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/EndlessScrollLoadMoreButtonActivity.kt" target="_blank">EndlessScrollLoadMoreButtonActivity</a></sup>
* Diff Util extension  <sup><a href="https://github.com/manneohlund/smart-recycler-adapter/blob/rc-3.0.0/sample/src/main/java/io/github/manneohlund/smartrecycleradapter/feature/DiffUtilActivity.kt" target="_blank">DiffUtilActivity</a></sup>

# Gradle  
#### Step 1. Add the JitPack repository to your build file  
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}  
```

#### Step 2. Add the dependency  
```groovy
dependencies {  
  implementation 'com.github.manneohlund:smart-recycler-adapter:3.0.0'
}
```

# Basic
### Basic adapter creation

```java
SmartRecyclerAdapter
  .items(items)
  .map(MoviePosterModel.class, PosterViewHolder.class)
  .map(MovieBannerModel.class, BannerViewHolder.class)
  .map(TopNewsModel.class, TopNewsViewHolder.class)
  .into(recyclerView);
 ```

# New nested SmartRecyclerAdapter from v2.0.0

New in `SmartRecyclerAdapter` v2.0.0 is support for nested recycler adapter.
Now you can easily build complex nested adapters without hustle and have full control of the adapter in your view controlling `Fragment` or `Activity`. 
Use the new `create()` method instead of the `into(recyclerView)` to create just the `SmartRecyclerAdapter` then set the adapter to the recycler view in your `ViewHolder`.
Just implement the `SmartAdapterHolder` interface in your `ViewHolder` and `SmartRecyclerAdapter` will handle the mapping.

### 1. Create your nested SmartRecyclerAdapter

```java
SmartRecyclerAdapter myWatchListSmartMovieAdapter = SmartRecyclerAdapter
  .items(myWatchListItems)
  .map(MovieModel.class, ThumbViewHolder.class)
  .addViewEventListener(
    ThumbViewHolder.class,
    R.id.event_on_click,
    (view, eventId, position) -> playMovie())
  .create();
````

### 2. Map myWatchListSmartMovieAdapter with MyWatchListViewHolder

```java
SmartRecyclerAdapter
  .items(items)
  .map(MoviePosterModel.class, PosterViewHolder.class)
  .map(MyWatchListModel.class, MyWatchListViewHolder.class)
  .map(MyWatchListViewHolder.class, myWatchListSmartMovieAdapter)
  .into(recyclerView);
```

### 3. Map myWatchListSmartMovieAdapter to MyWatchListViewHolder

```java
class MyWatchListViewHolder
    extends SmartAutoEventViewHolder<MyWatchListModel>
    implements SmartAdapterHolder {
    
  // Constructor here
    
  @Override
  public void setSmartRecyclerAdapter(SmartRecyclerAdapter smartRecyclerAdapter) {
    recyclerView.setLayoutManager(new LinearLayoutManager(context, HORIZONTAL, false));
    recyclerView.setAdapter(smartRecyclerAdapter);
  }

  public void bind(MyWatchListModel myWatchListModel) {
    // bind model data to views
  }
    
  public void unbind() {
    // optional unbinding of view data model
  }
}
```

### ViewHolder

Just extend your ViewHolder class with SmartViewHolder and pass in the target type ex `SmartViewHolder<Mail>`.  
Note that the constructor can both take `View` or `ViewGroup` as parameter, in this case `PosterViewHolder(ViewGroup parentView)` to avoid casting to ViewGroup while inflating.  
The `parentView` is the recyclerView.<br/>
The method `unbind` has an default implementation and is optional. 
##### Works with Android DataBinding! Just add the DataBinding LayoutInflater in `super` call. 🚀

```java
public class PosterViewHolder extends SmartViewHolder<MoviePosterModel> {

  public PostViewHolder(ViewGroup parentView) { 
    super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.poster_view, parentView, false)); 
  }
  
  @Override 
  public void bind(MoviePosterModel model) {
    Glide.with(imageView)
      .load(model.icon)
      .into(imageView);
  }
  
  @Override 
  public void unbind() {
    Glide.with(imageView).clear(imageView);
  }
} 
```

### View event listener
  
You can easily assign events to views and add an `ViewEventListener` to the SmartRecyclerAdapter for easy handling.<br/>
You must extend your `ViewHolder` with `SmartEventViewHolder`.

```java
SmartRecyclerAdapter
  .items(items)
  .map(MovieModel.class, MovieViewHolder.class)
  // You need to define your own view event listeners like onClickListener on a view
  .addViewEventListener((view, eventId, position) -> handleItemEvent())
  // Adds event listener for MovieViewHolder only
  .addViewEventListener(MovieViewHolder.class, (view, eventId, position) -> handleItemEvent())
  .into(recyclerView);
```
 
In your view holder, add eg `OnClickListener` to a view and call `notifyOnItemEvent`.<br/>
Your `ViewHolder` must extend `SmartEventViewHolder`. 
  
```java
class MovieViewHolder extends SmartEventViewHolder<MovieModel> {
  @Override
  public void bind(MovieModel movieModel) {
    imageView.setOnClickListener(view -> notifyOnItemEvent(view, R.id.action_play_movie));
  }
}
```

If you are lazy and want to auto assign a predefined `onClickListener` and `onLongClickListener` with eventIds `R.id.event_on_click` and `R.id.event_on_long_click`,
just extend your `ViewHolder` with `SmartAutoEventViewHolder`.

```java
class MovieViewHolder extends SmartAutoEventViewHolder<MovieModel>
```

And add event listener to `SmartRecyclerAdapter` builder.

```java
SmartRecyclerAdapter
  .items(items)
  .map(MovieModel.class, MovieViewHolder.class)
  /// Adds event listener for MovieViewHolder and adds View.OnClickListener with action R.id.event_on_click on view with id R.id.info_button
  .addViewEventListener(MovieViewHolder.class,
                        R.id.info_button, 
                        R.id.event_on_click, 
                        (view, eventId, position) -> openMovieInfo())
  .into(recyclerView);
```

### SmartEndlessScrollRecyclerAdapter

A popular feature in apps is to have endless scrolling with pagination, in other words load more items when user has scrolled to bottom.
With SmartEndlessScrollRecyclerAdapter you can achieve this.

#### 1. Create adapter
```java
SmartEndlessScrollRecyclerAdapter endlessScrollAdapter = SmartEndlessScrollRecyclerAdapter
  .items(items)
  .map(MovieModel.class, MovieViewHolder.class)
  .into(recyclerView);
```

#### 2. Set OnLoadMoreListener to your SmartEndlessScrollRecyclerAdapter

Called when scrolled to the last item and loading view is visible.

```java
endlessScrollAdapter.setOnLoadMoreListener(() -> {
  endlessScrollAdapter.addItems(moreItems);
});
```

#### More SmartEndlessScrollRecyclerAdapter features

Enable/Disable endless scrolling and thus removing the loading view.
`endlessScrollAdapter.setEndlessScrollEnabled(false);`

You can also set your custom loading/loadmore view.
`endlessScrollAdapter.setCustomLoadMoreLayoutResource(R.layout.your_custom_loadmore_view);`

### Adapter creation with ViewTypeResolver
  
If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.  
Note .map() call not needed in this case but you can combine if you want to.
  
```java
SmartRecyclerAdapter
  .items(items)
  .setViewTypeResolver((item, position) -> {
    if (item instanceof MovieTrailerModel) { 
      return MovieTrailerViewHolder.class;
    } else if (item instanceof MovieModel && ((MovieModel)item).isRatedR()) { 
      return RMovieViewHolder.class; 
    } return MovieViewHolder.class; // Add default view if needed, else SmartRecyclerAdapter will look at the base `.map` mapping
  })
  .into(recyclerView);
```

### RecyclableViewHolder

Sometimes a ViewHolder created by the Adapter cannot be recycled due to its transient state.
In order to fix this is to implement `Re` in your `SmartViewHolder` extension so that upon receiving this callback, 
Adapter can clear the animation(s) that effect the View's transient state and return <code>true</code> so that the View can be recycled.

```java
class MovieViewHolder 
    extends SmartViewHolder
    implements RecyclableViewHolder {
  @Override
  public boolean onFailedToRecycleView() {
    // Clear animations or other stuff
    return true; 
  }
}
```

### OnViewAttachedToWindowListener and OnViewDetachedFromWindowListener

If you want to catch when the view is attached and detached from the window in your ViewHolder you can implement `OnViewAttachedToWindowListener` and `OnViewDetachedFromWindowListener` in your `SmartViewHolder` extension.
Becoming detached from the window is not necessarily a permanent condition the consumer of an Adapter's views may choose to cache views offscreen while they are not visible, attaching and detaching them as appropriate.

```java
public class MovieViewHolder 
    extends SmartViewHolder 
    implements OnViewAttachedToWindowListener, 
               OnViewDetachedFromWindowListener { 

  @Override
  public void onViewAttachedToWindow() {
    // Restore
  }

  @Override
  public void onViewDetachedFromWindow() {
    // Cache
  }
}
```

### More SmartRecyclerAdapter features

```java
SmartRecyclerAdapter adapter = SmartRecyclerAdapter
    .items(items)
    .map(MovieModel.class, MovieViewHolder.class)
    .into(recyclerView);

// We can add more data
adapter.addItems(items);

// Add data at index with animation
adapter.addItem(0, item);

// Add data at index without animation
adapter.addItem(0, item, false);

// Remove item at index with animation
adapter.removeItem(0);

// Remove item at index without animation
adapter.removeItem(0, false);

// Replace item at index with animation
adapter.replaceItem(0, item);

// Replace item at index without animation
adapter.replaceItem(0, item, false);

// Get items by type
adapter.getItems(MovieModel.class);

// Delete all items in the list
adapter.clear();
```
