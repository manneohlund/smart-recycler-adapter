package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.viewholder.SmartViewHolder
import kotlin.reflect.KClass

/**
 * Type alias for swipe direction integer value.
 */
typealias Direction = Int

/**
 * Type alias for swipe flags integer.
 */
typealias SwipeFlags = Int

/**
 * Type alias for swipe lambda callbacks.
 */
typealias OnItemSwipedListener = (viewHolder: RecyclerView.ViewHolder, direction: Direction) -> Unit

/**
 * Defines basic functionality of the DragAndDropExtension.
 */
abstract class SwipeExtension : ItemTouchHelper.Callback() {

    /**
     * Sets target swipe flags.
     * @see ItemTouchHelper.LEFT
     *
     * @see ItemTouchHelper.RIGHT
     *
     * @param swipeFlags flags
     */
    abstract fun setSwipeFlags(swipeFlags: SwipeFlags)

    /**
     * Defines if item should be draggable after long press.
     * @param longPressDragEnabled should drag be enabled
     */
    abstract fun setLongPressDragEnabled(longPressDragEnabled: Boolean)

    /**
     * Sets target view holder types that should be draggable.
     * @param viewHolderTypes class types
     */
    abstract fun setViewHolderTypes(viewHolderTypes: List<KClass<SmartViewHolder<*>>>)

    /**
     * Sets the swipe listener.
     * @param onItemSwipedListener listener
     */
    abstract fun setOnItemSwipedListener(onItemSwipedListener: OnItemSwipedListener)
}
