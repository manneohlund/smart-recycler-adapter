package smartadapter.listener

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.Position
import smartadapter.SmartViewHolderType
import smartadapter.ViewEventId
import smartadapter.ViewId
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder

/**
 * Callback added in [smartadapter.SmartRecyclerAdapter] for view events listening in [SmartViewHolder] extensions.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
interface OnViewEventListener {

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
     * Default implementation returns [R.id.undefined] and any [SmartViewHolder] extensions must implement [ViewEventListenerHolder].
     *
     * Can be overridden with predefined view action ids.
     * [R.id.event_on_click]
     * [R.id.event_on_long_click]
     */
    val viewEventId: ViewEventId
        get() = R.id.undefined

    /**
     * Receiver of a view events.
     *
     * @param view source view which dispatched the action
     * @param viewEventId callback viewEventId
     * @param position the adapter position
     */
    fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position)
}

/**
 * Helper method to provide lambda call to interface instances of [OnViewEventListener].
 */
inline fun onViewEventListener(crossinline viewEvent: (
    view: View,
    viewEventId: ViewEventId,
    position: Position) -> Unit) = object : OnViewEventListener {
    override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
        viewEvent(view, viewEventId, position)
    }
}