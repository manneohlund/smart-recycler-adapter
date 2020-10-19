
# smart-recycler-adapter-diffutil

As of `smart-recycler-adapter:v5.0.0` diff util have been removed from `SmartRecyclerAdapter` and is added in this extension library `smart-recycler-adapter-diffutil`.

Essentially the `SmartRecyclerAdapter` will now hold a map of `SmartRecyclerAdapterBinder` that is the basic interface for `SmartRecyclerAdapter` binding extensions.

```kotlin
// If adapter items contains unspecified super type DiffPredicate bust be of type Any, DiffPredicate<Any>
private val predicate = object : DiffUtilExtension.DiffPredicate<Int> {
  override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
    return oldItem == newItem
  }
    
  override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
    return oldItem == newItem
  }
}

// Add SimpleDiffUtilExtension to the adapter
SmartRecyclerAdapter
  .items((0..100).toMutableList())
  .map(Integer::class, SimpleItemViewHolder::class)
  .add(SimpleDiffUtilExtension(predicate))
  .into(recyclerView)

// Add some new random items
smartRecyclerAdapter.diffSwapList((0..100).shuffled().toMutableList())
```

**See sample app section:** [#DiffUtil](#diffutil)