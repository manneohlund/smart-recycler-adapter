package smartadapter.listeners

import androidx.annotation.IdRes
import io.github.manneohlund.smartrecycleradapter.listeners.R
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.viewholder.SmartViewHolder

/**
 * Extends [OnViewEventListener] and contains the logic for the multi view holder views click for recycler adapter positions.
 */
class OnClickEventListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes
    override vararg val viewIds: ViewId = intArrayOf(R.id.undefined)
) : OnViewEventListener<ViewEvent.OnClick>(),
    OnCreateViewHolderListener {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        viewIds.forEach {
            with(findView(it, viewHolder)) {
                setOnClickListener { view ->
                    if (viewHolder is OnItemClickListener) {
                        viewHolder.onItemClick(
                            adapter,
                            viewHolder,
                            viewHolder.adapterPosition,
                            view
                        )
                    }
                    eventListener.postValue(
                        ViewEvent.OnClick(
                            adapter,
                            viewHolder,
                            viewHolder.adapterPosition,
                            view
                        )
                    )
                }
            }
        }
    }
}
