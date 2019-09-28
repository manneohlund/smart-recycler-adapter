package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.viewholder.SmartViewHolder
import kotlin.math.abs
import kotlin.reflect.KClass

/**
 * The basic implementation of [SwipeExtension] that [SwipeExtensionBuilder] by default sets.
 *
 * @see SwipeExtension
 */
open class BasicSwipeExtension : SwipeExtension() {

    private var longPressDragEnabled: Boolean = false
    private var swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    private var viewHolderTypes = listOf(SmartViewHolder::class)
    private var onItemSwipedListener: OnItemSwipedListener = { _, _ -> } // No op

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        var swipeFlags = 0
        for (viewHolderType in viewHolderTypes) {
            if (viewHolderType.java.isAssignableFrom(viewHolder.javaClass)) {
                swipeFlags = this.swipeFlags
                break
            }
        }
        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemSwipedListener(viewHolder, direction)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return longPressDragEnabled
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = 1 - abs(dX) / recyclerView.width
            viewHolder.itemView.alpha = alpha
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun setSwipeFlags(swipeFlags: Int) {
        this.swipeFlags = swipeFlags
    }

    override fun setLongPressDragEnabled(longPressDragEnabled: Boolean) {
        this.longPressDragEnabled = longPressDragEnabled
    }

    override fun setViewHolderTypes(viewHolderTypes: List<KClass<SmartViewHolder<*>>>) {
        this.viewHolderTypes = viewHolderTypes
    }

    override fun setOnItemSwipedListener(onItemSwipedListener: OnItemSwipedListener) {
        this.onItemSwipedListener = onItemSwipedListener
    }
}
