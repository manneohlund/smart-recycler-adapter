# smart-recycler-adapter

[![Release](https://jitpack.io/v/manneohlund/smart-recycler-adapter.svg)](https://jitpack.io/#manneohlund/smart-recycler-adapter)

Never code any boilerplate RecyclerAdapter again!  
This library will make it easy and painless to map your data item with a target ViewHolder.  

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
  implementation 'com.github.manneohlund:smart-recycler-adapter:2.0.0'
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

# Complex SmartRecyclerAdapter

New in `SmartRecyclerAdapter` v2.0.0 is support for nested recycler adapter.
Now you can easily build complex nested adapters without hustle and have full control in your view controlling `Fragment` or `Activity`.

```java
SmartRecyclerAdapter myWatchListSmartMovieAdapter = SmartRecyclerAdapter
  .items(myWatchListItems)
  .map(MovieModel.class, ThumbViewHolder.class)
  .addViewEventListener(
    ThumbViewHolder.class,
    R.id.action_on_click,
    (view, actionId, position) -> playMovie())
  .create();

SmartRecyclerAdapter
  .items(items)
  .map(MoviePosterModel.class, PosterViewHolder.class)
  .map(MyWatchListModel.class, MyWatchListViewHolder.class)
  .map(MyWatchListViewHolder.class, myWatchListSmartMovieAdapter)
  .into(recyclerView);
```

### SmartRecyclerAdapter methods  
  
```java
SmartRecyclerAdapter adapter = SmartRecyclerAdapter.init(this).items(items).map(Post.class, MainViewHolder.class).into(recyclerView);  

// We can add more items
adapter.addItems(newItems);

// We can get items by type
adapter.getItems(Post.class);

// Delete all items in the list
adapter.clear();
```
