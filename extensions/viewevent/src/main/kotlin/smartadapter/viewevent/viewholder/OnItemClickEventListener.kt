package smartadapter.viewholder

import smartadapter.viewevent.models.ViewEvent

/**
 * Smart adapter item view click listener.
 */
interface OnItemClickEventListener {

    fun onViewEvent(event: ViewEvent.OnClick)
}
