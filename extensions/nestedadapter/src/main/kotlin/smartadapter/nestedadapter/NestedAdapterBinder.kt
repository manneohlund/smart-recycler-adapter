package smartadapter.nestedadapter

import androidx.recyclerview.widget.RecyclerView
import smartadapter.RecyclerViewBinder
import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartViewHolder

/**
 * Defines the basic NestedAdapterBinder.
 */
interface NestedAdapterBinder {

    /**
     * Reusable [SmartAdapterBuilder] to create a new [SmartRecyclerAdapter] for each [SmartViewHolder] subclass of type [SmartNestedRecyclerViewHolder]
     */
    val smartRecyclerAdapterBuilder: SmartAdapterBuilder

    /**
     * With [RecyclerViewBinder] you can target specific [SmartNestedRecyclerViewHolder] subclasses to define and set [RecyclerView] properties like [RecyclerView.LayoutManager].
     */
    var recyclerViewBinder: RecyclerViewBinder?
}