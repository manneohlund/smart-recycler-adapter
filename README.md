# smart-recycler-adapter
Never code any boilerplate RecyclerAdapter again!
This library will make it easy and painless to map your data item with a target ViewHolder.

# Gradle
#### Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
#### Step 2. Add the dependency
```groovy
dependencies {
    compile 'com.github.manneohlund:smart-recycler-adapter:1.0.0'
}
```

# Usage
### Basic adapter creation

```java
SmartRecyclerAdapter
    .init(this) // Must be Activity or Fragment reference
    .items(items)
    .map(Header.class, HeaderViewHolder.class)
    .map(Mail.class, MainViewHolder.class)
    .map(Footer.class, FooterViewHolder.class)
    .into(recyclerView);
```

### ViewHolder

Just extend your ViewHolder class with SmartViewHolder and pass in the target type ex `SmartViewHolder<Mail>`.
Note that the constructor must take `ViewGroup` as parameter, in this case ```MailViewHolder(ViewGroup parentView)```.
The `parentView` is the recyclerView.

```java
public static class MailViewHolder extends SmartViewHolder<Mail> {

    public MailViewHolder(ViewGroup parentView) {
        super(LayoutInflater.from(parentView.getContext()).inflate(android.R.layout.simple_list_item_1, parentView, false));
    }

    @Override
    public void bind(Mail mail) {
        ((TextView)itemView).setText(mail.toString());
    }
}
```

### View event listener

You can easily assign events to views and add an `ViewEventListener` to the SmartRecyclerAdapter for easy handling.

```java
SmartRecyclerAdapter
    .init(this) // Must be Activity or Fragment reference
    .items(items)
    .map(Mail.class, MainViewHolder.class)
    .setViewEventListener(new ViewEventListener() {
        @Override
        public void onViewEvent(View view, int actionId, int position) {
            // Handle event
        }
    })
    .into(recyclerView);
```
In your view holder, add event caller to view and pass the view and an action id.

```java
    @Override
    public void bind(Mail mail) {
        rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyOnItemEvent(view, ACTION_ITEM_TAP);
                }
            });
        sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyOnItemEvent(view, ACTION_SEND);
                }
            });
    }
```

### Advanced adapter creation

If you want to bind one data type with different view holders depending on some attribute you can set a ViewTypeResolver.
Note .map() call not needed in this case but you can combine if you want to.
You can also set an OnViewDetachedFromWindowListener for immediate view holder detach handling.

```java
SmartRecyclerAdapter
    .init(this)
    .items(items)
    .map(Header.class, HeaderViewHolder.class)
    .map(Footer.class, FooterViewHolder.class)
    .setViewTypeResolver(new ViewTypeResolver() {
        @Override
        public Class<? extends SmartViewHolder> getViewType(Object item, int position) {
            if (item instanceof ErrorMail) {
                return ErrorMailViewHolder.class;
            } else if (item instanceof Mail && ((Mail)item).isWarning) {
                return WarningMailViewHolder.class;
            }
            return MailViewHolder.class;
        }
    })
    .setOnViewDetachedFromWindowListener(new OnViewDetachedFromWindowListener() {
        @Override
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            if (holder instanceof ImageViewHolder) {
                ImageCacheManager.getInstance().cancelAsyncTask(holder);
            }
        }
    })
    .into(recyclerView);
```

### SmartRecyclerAdapter methods

```java
SmartRecyclerAdapter adapter = SmartRecyclerAdapter.init(this).items(items).map(Mail.class, MainViewHolder.class).into(recyclerView);

// We can add more items
adapter.addItems(newItems);

// We can get items by type
adapter.getItems(Mail.class);

// Delete all items in the list
adapter.clear();
```
