package smartadapter.viewevent.listeners

import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.viewevent.models.ViewEvent
import smartadapter.viewholder.CustomViewEventListenerHolder
import smartadapter.viewholder.SmartViewHolder

/**
 * Contains the logic for passing itself to a [SmartViewHolder]
 * via [CustomViewEventListenerHolder] interface to enable posting of custom [ViewEvent].
 */
class OnCustomViewEventListener(
    override var eventListener: (ViewEvent) -> Unit
) : OnViewEventListener<ViewEvent>,
    SmartViewHolderBinder,
    OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        if (viewHolder is CustomViewEventListenerHolder) {
            viewHolder.customViewEventListener = eventListener
        }
    }
}
