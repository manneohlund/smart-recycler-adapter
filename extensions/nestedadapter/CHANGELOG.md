# v1.0.0-beta01

### Added
* `RecyclerViewBinder` invocation for SmartNestedRecyclerViewHolder for easy RecyclerView configuration in SmartAdapterBuilder
* `reuseParentAdapterRecycledViewPool` to reuse parent SmartRecyclerAdapter RecyclerView.RecycledViewPool into nested adapters.
  Default is shared recycledViewPool for nested adapters

# v1.0.0-alpha01

### Initial release contains

* `SmartNestedAdapterBinder` Holds map of multiple nestedAdapters that binds with main parent SmartRecyclerAdapter data items.
* `SmartNestedItem` Defining the data model sub/nested items
* `SmartNestedRecyclerViewHolder` Holds reference to RecyclerView