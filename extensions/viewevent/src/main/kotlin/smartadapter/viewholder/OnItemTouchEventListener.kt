package smartadapter.viewholder

import smartadapter.viewevent.models.ViewEvent

/**
 * Smart adapter item view touch listener.
 */
interface OnItemTouchEventListener {

    fun onViewEvent(event: ViewEvent.OnTouchEvent)
}
