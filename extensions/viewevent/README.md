
# smart-recycler-adapter-viewevent

As of `smart-recycler-adapter:v5.0.0` all ViewEvent listeners have been removed from `SmartRecyclerAdapter` and added in this extension library `smart-recycler-adapter-viewevent`.
Essentially the `SmartRecyclerAdapter` will now hold a list of `SmartViewHolderBinder` that can implement any of these interfaces to listen to the adapter view holder stages:
* `OnSmartRecycleAdapterCreatedListener` Invoked from `SmartRecyclerAdapter` init
* `OnCreateViewHolderListener` Invoked from `SmartRecyclerAdapter.onCreateViewHolder`
* `OnBindViewHolderListener` Invoked from `SmartRecyclerAdapter.onBindViewHolder`
* `OnViewAttachedToWindowListener` Invoked from `SmartRecyclerAdapter.onViewAttachedToWindow`
* `OnViewDetachedFromWindowListener` Invoked from `SmartRecyclerAdapter.onViewDetachedFromWindow`

This way all extension libraries has full control over the view holder lifecycle stages and can be hooked with various listeners and state holders.<br/>
You can create any type of `SmartViewHolderBinder` extension and implement any number of the listed adapter listeners.

For example the `OnClickEventListener` implements `OnViewEventListener<ViewEvent.OnClick>`, `SmartViewHolderBinder` & `OnCreateViewHolderListener` and looks like this:

```kotlin
open class OnClickEventListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes override vararg val viewIds: ViewId = intArrayOf(R.id.undefined),
    override var eventListener: (ViewEvent.OnClick) -> Unit
) : OnViewEventListener<ViewEvent.OnClick>,
    SmartViewHolderBinder,
    OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        viewIds.forEach {
            with(findView(it, viewHolder)) {
                setOnClickListener { view ->
                    val event = ViewEvent.OnClick(adapter, viewHolder, viewHolder.adapterPosition, view)
                    (viewHolder as? OnItemClickEventListener)?.let {
                        viewHolder.onViewEvent(event)
                    }
                    eventListener.invoke(event)
                }
            }
        }
    }
}
```