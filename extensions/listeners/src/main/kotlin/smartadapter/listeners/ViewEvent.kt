package smartadapter.listeners

import android.view.MotionEvent
import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartViewHolder

/**
 * ViewEvent is the data type passed through the [OnViewEventListener.eventListener]
 */
open class ViewEvent(
    val adapter: SmartRecyclerAdapter,
    val viewHolder: SmartViewHolder<*>,
    val position: Position,
    val view: View
) {
    class OnTouchEvent(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<*>,
        position: Position,
        view: View,
        val event: MotionEvent
    ) : ViewEvent(adapter, viewHolder, position, view)

    class OnClick(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<*>,
        position: Position,
        view: View
    ) : ViewEvent(adapter, viewHolder, position, view)

    class OnLongClick(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<*>,
        position: Position,
        view: View
    ) : ViewEvent(adapter, viewHolder, position, view)

    class OnItemSelected(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<*>,
        position: Position,
        view: View,
        val isSelected: Boolean
    ) : ViewEvent(adapter, viewHolder, position, view)
}
