package smartadapter.listeners

import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.viewholder.GenericViewEventViewModelHolder
import smartadapter.viewholder.SmartViewHolder

/**
 * Extends [OnViewEventListener] and contains the logic for passing itself to a [SmartViewHolder]
 * via [GenericViewEventViewModelHolder] interface to enable posting of custom [ViewEvent].
 */
class OnCustomViewEventListener : OnViewEventListener<ViewEvent>(), OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        if (viewHolder is GenericViewEventViewModelHolder) {
            viewHolder.customViewEventListener = this
        }
    }

    fun postViewEvent(event: ViewEvent) = eventListener.postValue(event)
}
