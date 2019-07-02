# smart-recycler-adapter

[![Release](https://jitpack.io/v/manneohlund/smart-recycler-adapter.svg)](https://jitpack.io/#manneohlund/smart-recycler-adapter)

Never code any boilerplate RecyclerAdapter again!  
This library will make it easy and painless to map your data item with a target ViewHolder.

<p align="center">
  <img width="240" height="499" src="https://user-images.githubusercontent.com/11292383/60390746-cc723f00-9add-11e9-8695-65315836a6f8.gif">
</p>

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
  implementation 'com.github.manneohlund:smart-recycler-adapter:2.0.1'
}
```

# New complex SmartRecyclerAdapter v2.0.0

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
    R.id.action_on_click,
    (view, actionId, position) -> playMovie())
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
    
    @NonNull
    @Override
    public SmartRecyclerAdapter getSmartRecyclerAdapter() {
        return this.smartRecyclerAdapter;
    }
    
    @Override
    public void setSmartRecyclerAdapter(SmartRecyclerAdapter smartRecyclerAdapter) {
        recyclerView.setLayoutManager(LinearLayoutManager(recyclerView.context, HORIZONTAL, false);
        recyclerView.adapter = (RecyclerView.Adapter) smartRecyclerAdapter;
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    public void bind(MyWatchListModel myWatchListModel) {
        // bind model data to views
    }
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
  
### ViewHolder

Just extend your ViewHolder class with SmartViewHolder and pass in the target type ex `SmartViewHolder<Mail>`.  
Note that the constructor must take `ViewGroup` as parameter, in this case `PosterViewHolder(ViewGroup parentView)`.  
The `parentView` is the recyclerView.<br/>
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
  .addViewEventListener((view, actionId, position) -> handleItemEvent())
  // Adds event listener for MovieViewHolder only
  .addViewEventListener(MovieViewHolder.class, (view, actionId, position) -> handleItemEvent())
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

If you are lazy and want to auto assign a predefined `onClickListener` and `onLongClickListener` with actionIds `R.id.action_on_click` and `R.id.action_on_long_click`,
just extend your `ViewHolder` with `SmartAutoEventViewHolder`.

```java
class MovieViewHolder extends SmartAutoEventViewHolder<MovieModel>
```

And add event listener to `SmartRecyclerAdapter` builder.

```java
SmartRecyclerAdapter
  .items(items)
  .map(MovieModel.class, MovieViewHolder.class)
  /// Adds event listener for MovieViewHolder and adds View.OnClickListener with action R.id.action_on_click on view with id R.id.info_button
  .addViewEventListener(MovieViewHolder.class,
                        R.id.info_button, 
                        R.id.action_on_click, 
                        (view, actionId, position) -> openMovieInfo())
  .into(recyclerView);
```
  
### Adapter creation with ViewTypeResolver
  
If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.  
Note .map() call not needed in this case but you can combine if you want to.  
You can also set an OnViewDetachedFromWindowListener for immediate view holder detach handling.
  
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
  .setOnViewDetachedFromWindowListener(holder -> {
    if (holder instanceof ImageViewHolder) {
      ImageCacheManager.getInstance().cancelAsyncTask(holder);
    }
  })
  .into(recyclerView);
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
