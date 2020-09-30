package smartadapter.listeners

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartViewHolder

/**
 * Smart adapter item view click listener.
 */
interface OnItemClickListener {

    /**
     * Called when [OnClickEventListener] bound view triggers an [ViewEvent.OnClick] event.
     */
    fun onItemClick(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<*>,
        position: Position,
        view: View
    )
}
