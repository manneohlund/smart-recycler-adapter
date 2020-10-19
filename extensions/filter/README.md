
# smart-recycler-adapter-filter

With the `FilterExtension` extension you can synchronously or asynchronously filter your items.

### Create SmartRecyclerAdapter

```kotlin
SmartRecyclerAdapter.items(items)
  .map(String::class, HeaderViewHolder::class)
  .map(Int::class, FilterItemViewHolder::class)
  .add(OnClickEventListener {
    // Handle click event
  })
  .add(
    FilterExtension(
      filterPredicate = { item, constraint ->
        when (item) {
          is Int -> item.toString().contains(constraint)
          else -> true
        }
      },
      loadingStateListener = { isLoading ->
        // Set loading progress visibility
      }
    )
  )
  .into(recyclerView)
```

###  Set search view filter
```kotlin
searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
    // Call some filter function ex: filter(newText)
})
```

###  Filter
```kotlin
fun filter(query: String?) {
  val filterExtension: FilterExtension = smartAdapter.get()

  filterExtension.filter(lifecycleScope, query, autoSetNewItems = true)
}
```