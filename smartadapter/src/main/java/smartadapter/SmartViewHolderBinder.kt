package smartadapter

import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.viewholder.SmartViewHolder

interface SmartViewHolderBinder {

    /**
     * Default implementation returns [SmartViewHolder] class which
     * [smartadapter.SmartRecyclerAdapter] will bind to all [SmartViewHolder] extensions.
     *
     * Can be overridden to a specific target [SmartViewHolder] extension.
     */
    val viewHolderType: SmartViewHolderType
        get() = SmartViewHolder::class

    /**
     * Default implementation returns [R.id.undefined] which will point to the root view of the view in the view holder.
     *
     * Can be overridden to target specific view ids.
     */
    val viewIds: IntArray
        get() = intArrayOf(R.id.undefined)
}
