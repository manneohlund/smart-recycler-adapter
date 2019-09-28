package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartViewHolderType
import smartadapter.listener.OnItemMovedListener
import smartadapter.viewholder.SmartViewHolder

/**
 * The basic implementation of [DragAndDropExtension] that [DragAndDropExtensionBuilder] by default sets.
 *
 * @see DragAndDropExtension
 */
open class BasicDragAndDropExtension : DragAndDropExtension() {

    override var dragFlags: Int = 0
    override var touchHelper: ItemTouchHelper? = null
    override var viewHolderTypes: List<SmartViewHolderType> = listOf(SmartViewHolder::class)
    override var longPressDragEnabled: Boolean = false
    override var onItemMovedListener: OnItemMovedListener = { _, _ -> } // Noop

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        for (viewHolderType in viewHolderTypes) {
            if (viewHolderType.java.isAssignableFrom(target.javaClass) && viewHolder.javaClass == target.javaClass) {
                onItemMovedListener(viewHolder, target)
                return true
            }
        }
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Direction) {
        // Noop
    }

    override fun isLongPressDragEnabled(): Boolean {
        return longPressDragEnabled
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setupDragAndDrop(recyclerView: RecyclerView) {
        if (dragFlags == 0) {
            val gridDragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            val linearDragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN

            dragFlags = if (recyclerView.layoutManager is GridLayoutManager)
                gridDragFlags
            else
                linearDragFlags
        }
    }
}
