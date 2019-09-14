package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartViewHolderType
import smartadapter.listener.OnItemMovedListener

/**
 * Defines basic functionality of the DragAndDropExtension.
 */
abstract class DragAndDropExtension : ItemTouchHelper.Callback() {

    /**
     * Sets target drag flags.
     * @see ItemTouchHelper.LEFT
     * @see ItemTouchHelper.RIGHT
     * @see ItemTouchHelper.UP
     * @see ItemTouchHelper.DOWN
     */
    abstract var dragFlags: Int

    /**
     * Defines [ItemTouchHelper] for custom item view touch handling.
     */
    abstract var touchHelper: ItemTouchHelper?

    /**
     * Defines target view holder types that should be draggable.
     */
    abstract var viewHolderTypes: List<SmartViewHolderType>

    /**
     * Defines if item should be draggable after long press.
     */
    abstract var longPressDragEnabled: Boolean

    /**
     * Defines the drag/move listener
     */
    abstract var onItemMovedListener: OnItemMovedListener

    /**
     * Defines the draggable flags or binds touch listener to target drag view.
     */
    abstract fun setupDragAndDrop(recyclerView: RecyclerView)
}
