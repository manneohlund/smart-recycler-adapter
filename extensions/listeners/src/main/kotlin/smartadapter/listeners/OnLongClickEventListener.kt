package smartadapter.listeners

import androidx.annotation.IdRes
import io.github.manneohlund.smartrecycleradapter.listeners.R
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.ViewId
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.viewholder.SmartViewHolder

/**
 * Extends [OnViewEventListener] and contains the logic for the multi view holder views click for recycler adapter positions.
 */
class OnLongClickEventListener(
    @IdRes override vararg val viewIds: ViewId = intArrayOf(R.id.undefined)
) : OnViewEventListener<ViewEvent.OnLongClick>(),
    SmartViewHolderBinder,
    OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        viewIds.forEach {
            with(findView(it, viewHolder)) {
                setOnLongClickListener { view ->
                    if (viewHolder is OnItemLongClickListener) {
                        viewHolder.onItemLongClick(
                            adapter,
                            viewHolder,
                            viewHolder.adapterPosition,
                            view
                        )
                    }
                    eventListener.postValue(
                        ViewEvent.OnLongClick(
                            adapter,
                            viewHolder,
                            viewHolder.adapterPosition,
                            view
                        )
                    )
                    true
                }
            }
        }
    }
}
