package smartadapter.nestedadapter

import android.os.Parcelable
import smartadapter.RecyclerViewBinder
import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.extension.SmartViewHolderBinder
import smartadapter.listener.OnBindViewHolderListener
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.listener.OnViewRecycledListener
import smartadapter.viewholder.SmartViewHolder

/**
 * Holds map of multiple [nestedAdapters] that binds with main parent [SmartRecyclerAdapter] data items.
 *
 * All data items must implement [SmartNestedItem] to resolve [SmartNestedItem.items].
 *
 * All [SmartViewHolder] must implement [SmartNestedRecyclerViewHolder] in order to bind the recyclerView to target [nestedAdapters].
 *
 * [SmartNestedAdapterBinder] also saves scroll states for all [nestedAdapters].
 */
class SmartNestedAdapterBinder(
    override val identifier: Any = SmartNestedAdapterBinder::class,
    override val viewHolderType: SmartViewHolderType,
    val smartRecyclerAdapterBuilder: SmartAdapterBuilder,
    var recyclerViewBinder: RecyclerViewBinder? = null
) : SmartViewHolderBinder,
    OnCreateViewHolderListener,
    OnBindViewHolderListener,
    OnViewRecycledListener {

    private val nestedAdapters = mutableMapOf<SmartViewHolder<Any>, SmartRecyclerAdapter>()
    private val scrollStates = mutableMapOf<Int, Parcelable?>()

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        if (!viewHolderType.isInstance(viewHolder) && nestedAdapters.contains(viewHolder)) {
            return
        }

        if (!nestedAdapters.contains(viewHolder)) {
            nestedAdapters[viewHolder] = smartRecyclerAdapterBuilder.create()
        }

        with((viewHolder as SmartNestedRecyclerViewHolder).recyclerView) {
            recyclerViewBinder?.invoke(viewHolder, this)
        }
    }

    override fun onBindViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        if (!viewHolderType.isInstance(viewHolder) && !nestedAdapters.contains(viewHolder)) {
            return
        }

        val items = (adapter.getItem(viewHolder.adapterPosition) as SmartNestedItem<*>).items
        (viewHolder as SmartNestedRecyclerViewHolder).recyclerView.adapter =
            nestedAdapters[viewHolder]!!.also {
                it.setItems(items as MutableList<*>)
            }

        // Restore scroll state
        (viewHolder as SmartNestedRecyclerViewHolder).recyclerView.layoutManager?.onRestoreInstanceState(scrollStates[viewHolder.adapterPosition])
    }

    override fun onViewRecycled(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {
        if (!viewHolderType.isInstance(viewHolder) && !nestedAdapters.contains(viewHolder)) {
            return
        }

        // Save scroll state
        scrollStates[viewHolder.adapterPosition] = (viewHolder as SmartNestedRecyclerViewHolder).recyclerView.layoutManager?.onSaveInstanceState()
    }
}
