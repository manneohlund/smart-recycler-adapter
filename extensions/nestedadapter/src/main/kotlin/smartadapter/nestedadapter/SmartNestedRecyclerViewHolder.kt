package smartadapter.nestedadapter

import androidx.recyclerview.widget.RecyclerView
import smartadapter.viewholder.SmartViewHolder

/**
 * Holds reference to a [RecyclerView] for a [SmartViewHolder] subclass.
 */
interface SmartNestedRecyclerViewHolder {

    /**
     * Override and assign this reference to the target [RecyclerView] in a [SmartViewHolder] subclass.
     */
    val recyclerView: RecyclerView
}