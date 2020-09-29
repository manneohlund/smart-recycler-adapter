package smartadapter.listeners

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Smart adapter item view selection listener.
 */
interface OnItemSelectListener {

    /**
     * Called when [OnMultiItemSelectListener] bound view triggers an [ViewEvent.OnItemSelected] event.
     * Overrides the [OnMultiItemSelectListener.isSelected] call.
     */
    fun onItemSelect(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View,
        isSelected: Boolean
    )
}
