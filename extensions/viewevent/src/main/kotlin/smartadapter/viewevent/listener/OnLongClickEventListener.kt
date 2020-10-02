package smartadapter.viewevent.listener

import androidx.annotation.IdRes
import io.github.manneohlund.smartrecycleradapter.viewevent.R
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.findView
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewholder.OnItemLongClickEventListener
import smartadapter.viewholder.SmartViewHolder

/**
 * Contains the logic for the multi view holder views click for recycler adapter positions.
 */
class OnLongClickEventListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes override vararg val viewIds: ViewId = intArrayOf(R.id.undefined),
    override var eventListener: (ViewEvent.OnLongClick) -> Unit
) : OnViewEventListener<ViewEvent.OnLongClick>,
    SmartViewHolderBinder,
    OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        viewIds.forEach {
            with(findView(it, viewHolder)) {
                setOnLongClickListener { view ->
                    val event = ViewEvent.OnLongClick(
                        adapter,
                        viewHolder,
                        viewHolder.adapterPosition,
                        view
                    )
                    if (viewHolder is OnItemLongClickEventListener) {
                        viewHolder.onViewEvent(event)
                    }
                    eventListener.invoke(event)
                    true
                }
            }
        }
    }
}
