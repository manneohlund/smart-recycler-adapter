package smartadapter.viewholder

import smartadapter.viewevent.models.ViewEvent

/**
 * Smart adapter item view long click listener.
 */
interface OnItemLongClickEventListener {

    fun onViewEvent(event: ViewEvent.OnLongClick)
}
