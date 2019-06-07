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
  implementation 'com.github.manneohlund:smart-recycler-adapter:1.5.0'
}
```

# Usage  
### Basic adapter creation  

```java
SmartRecyclerAdapter
  .items(items)
  .map(Header.class, HeaderViewHolder.class)
  .map(Post.class, PostViewHolder.class)
  .map(Footer.class, FooterViewHolder.class)
  .into(recyclerView);
 ```
  
### ViewHolder  

Just extend your ViewHolder class with SmartViewHolder and pass in the target type ex `SmartViewHolder<Mail>`.  
Note that the constructor must take `ViewGroup` as parameter, in this case `PostViewHolder(ViewGroup parentView)`.  
The `parentView` is the recyclerView.<br/>
##### Works with Android DataBinding! Just add the DataBinding LayoutInflater in `super` call. 🚀

```java
public static class PostViewHolder extends SmartViewHolder<Post> {

  public PostViewHolder(ViewGroup parentView) { 
    super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.post_view, parentView, false)); 
  }
  
  @Override 
  public void bind(Post post) { 
    title.setText(post.toString()); 
  }
} 
```
  
### View event listener  
  
You can easily assign events to views and add an `ViewEventListener` to the SmartRecyclerAdapter for easy handling.<br/>
See `R.id.action_on_click` and `R.id.action_on_long_click`.<br/>
More events are coming.

```java
SmartRecyclerAdapter
  .items(items)
  .map(Post.class, PostViewHolder.class)
  // Adds event listener for PostViewHolder
  .addViewEventListener(PostViewHolder.class, (view, actionId, position) -> itemClick())
  // Adds event listener for PostViewHolder and adds View.OnClickListener with action R.id.action_on_click on view with id R.id.more_button
  .addViewEventListener(PostViewHolder.class, R.id.more_button, R.id.action_on_click, (view, actionId, position) -> openMore())
  .into(recyclerView);
```
 
In your view holder, add event caller to view and pass the view and an action id.  
  
```java
@Override
public void bind(Post post) {
  // Set other event listeners 
  sendButton.setOnClickListener(view -> notifyOnItemEvent(view, R.id.action_send));
}
```
  
### Advanced adapter creation  
  
If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.  
Note .map() call not needed in this case but you can combine if you want to.  
You can also set an OnViewDetachedFromWindowListener for immediate view holder detach handling.  
  
```java
SmartRecyclerAdapter
  .items(items)
  .map(Header.class, HeaderViewHolder.class)
  .map(Footer.class, FooterViewHolder.class)
  .map(Post.class, PostViewHolder.class)
  .setViewTypeResolver((item, position) -> {
    if (item instanceof ErrorPost) { 
      return ErrorPostViewHolder.class;
    } else if (item instanceof Post && ((Post)item).isWarning) { 
      return WarningPostViewHolder.class; 
    } return null; // Add default view if needed, else SmartRecyclerAdapter will look at the base `.map` mapping
  })
  .setOnViewDetachedFromWindowListener(holder -> {
    if (holder instanceof ImageViewHolder) {
      ImageCacheManager.getInstance().cancelAsyncTask(holder);
    }
  })
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
