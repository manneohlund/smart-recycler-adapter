package smartadapter.listeners

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Smart adapter item view long click listener.
 */
interface OnItemLongClickListener {

    /**
     * Called when [OnClickEventListener] bound view triggers an [ViewEvent.OnLongClick] event.
     */
    fun onItemLongClick(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View
    )
}
