# v1.0.0-beta02

### Added
* Added identifier override
* Added androidx.appcompat:appcompat for runtime resolving R.attr.selectableItemBackground

### Removed
* Removed SmartAdapterBuilderExt with helper methods

### Moved
* Moved binder and util packages to extensions

# v1.0.0-beta02

### Added

* Feature enableAll & disableAll selectable items functionality. `selectableItemType` will narrow down to valid selectable target item type. 

# v1.0.0-beta01

Bumped to beta

### Fixed
* Fix dragFlags for DragAndDropEventBinder depending on recyclerView is LinearLayoutManager
* Fix potential crash on collections item position swap

# v1.0.0-alpha02

### Fixed
Fix viewholder package location to package smartadapter.viewevent.viewholder e9f2a303

# v1.0.0-alpha01

### Initial release contains

* ViewEvent listeners
* SmartStateHolder with OnMultiItemSelectListeners
* Drag & Drop
* Swipe dismiss
* ViewEventApplicationViewModel
* ViewEventViewModel