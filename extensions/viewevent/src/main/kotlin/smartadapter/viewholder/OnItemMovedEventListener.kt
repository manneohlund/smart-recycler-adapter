package smartadapter.viewholder

import smartadapter.viewevent.models.ViewEvent

interface OnItemMovedEventListener {

    /**
     * Called when event bound view triggers an triggers an [ViewEvent.OnItemMoved] event.
     */
    fun onItemMoved(event: ViewEvent.OnItemMoved)
}
