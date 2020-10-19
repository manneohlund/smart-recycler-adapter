# v1.0.0-beta01

### Added
* `kotlinx-coroutines-android` for fast threading.
* `lifecycle-runtime-ktx` in combination with coroutines for lifecycle aware launch/cancellation of coroutines.
* `diffSwapList` async method with lifecycleScope coroutine job launch for heavy data computation.
* `cancelDiffSwapJob` method to cancel ongoing coroutine job.
* `loadingStateListener` for async loading state callback.

### Removed
* SmartAdapterBuilderExt with extension methods

# v1.0.0-alpha01

### Initial release contains
* `SimpleDiffUtilExtension` basic implementation of `DiffUtilExtension` 
* `DiffUtilExtension` defining the basic diff util.
* `SmartRecyclerAdapterExt` helper extension methods getDiffUtil, diffSwapList
* `SmartAdapterBuilderExt` helper extension methods to add extension