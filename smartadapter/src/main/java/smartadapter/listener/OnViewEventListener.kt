package smartadapter.listener

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import kotlin.reflect.KClass

typealias SmartViewHolderType = KClass<out SmartViewHolder<*>>
typealias ViewId = Int
typealias ViewEventId = Int
typealias Position = Int

/**
 * Callback added in [smartadapter.SmartRecyclerAdapter] for view events listening in [SmartViewHolder] extensions.
 */
interface OnViewEventListener {

    /**
     * Default implementation returns [SmartViewHolder] class which
     * [smartadapter.SmartRecyclerAdapter] will bind to all [SmartViewHolder] extensions.
     *
     *
     * Can be overridden to a specific target [SmartViewHolder] extension.
     *
     * @return [SmartViewHolder].class
     */
    val viewHolderType: SmartViewHolderType
        get() = SmartViewHolder::class

    /**
     * Default implementation returns [R.id.undefined] which will point to the root view of the view in the view holder.
     *
     *
     * Can be overridden to target specific view id.
     *
     * @return [R.id.undefined]
     */
    val viewId: ViewId
        get() = R.id.undefined

    /**
     * Default implementation returns [R.id.undefined] and any [SmartViewHolder] extensions must implement [ViewEventListenerHolder].
     *
     *
     * Can be overridden with predefined view action ids.
     * [R.id.event_on_click]
     * [R.id.event_on_long_click]
     *
     * @return [R.id.undefined]
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
