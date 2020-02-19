package smartadapter.listener

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewEventId
import smartadapter.ViewId
import smartadapter.viewholder.SmartViewHolder

interface ViewEvent {
    val event: Any
}

inline class OnViewEvent(
    override val event: (View, ViewEventId, Position) -> Unit
) : ViewEvent

inline class OnSmartViewEvent(
    override val event: (View, ViewEventId, SmartRecyclerAdapter, Position) -> Unit
) : ViewEvent

/**
 * Callback added in [smartadapter.SmartRecyclerAdapter] for view events listening in [SmartViewHolder] extensions.
 */
interface OnViewEventListener<T : Any> {
    /**
     * Default implementation returns [SmartViewHolder] class which
     * [smartadapter.SmartRecyclerAdapter] will bind to all [SmartViewHolder] extensions.
     *
     * Can be overridden to a specific target [SmartViewHolder] extension.
     */
    val viewHolderType: SmartViewHolderType
        get() = SmartViewHolder::class

    /**
     * Default implementation returns [R.id.undefined] which will point to the root view of the view in the view holder.
     *
     * Can be overridden to target specific view id.
     */
    val viewId: ViewId
        get() = R.id.undefined

    /**
     * Listener that can reference anything.
     */
    val listener: T
}
