package smartadapter.listeners

import android.view.MotionEvent
import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * ViewEvent is the data type passed through the [OnViewEventListener.eventListener]
 */
open class ViewEvent(
    val adapter: SmartRecyclerAdapter,
    val position: Position,
    val view: View
) {
    class OnTouchEvent(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View,
        val event: MotionEvent
    ) : ViewEvent(adapter, position, view)

    class OnClick(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View
    ) : ViewEvent(adapter, position, view)

    class OnLongClick(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View
    ) : ViewEvent(adapter, position, view)

    class OnItemSelected(
        adapter: SmartRecyclerAdapter,
        position: Position,
        view: View,
        val isSelected: Boolean
    ) : ViewEvent(adapter, position, view)
}
