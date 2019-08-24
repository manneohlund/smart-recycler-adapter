package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import smartadapter.listener.OnItemMovedListener;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Defines basic functionality of the DragAndDropExtension.
 */
public abstract class DragAndDropExtension extends ItemTouchHelper.Callback {

    /**
     * Sets target drag flags.
     * @see ItemTouchHelper#LEFT
     * @see ItemTouchHelper#RIGHT
     * @see ItemTouchHelper#UP
     * @see ItemTouchHelper#DOWN
     * @param dragFlags target flags
     */
    abstract public void setDragFlags(int dragFlags);

    /**
     * Defines if item should be draggable after long press.
     * @param longPressDragEnabled should drag be enabled
     */
    abstract public void setLongPressDragEnabled(boolean longPressDragEnabled);

    /**
     * Defines the draggable flags or binds touch listener to target drag view.
     * @param recyclerView target RecyclerView
     */
    abstract protected void setupDragAndDrop(@NonNull RecyclerView recyclerView);

    /**
     * Sets {@link ItemTouchHelper} for custom item view touch handling.
     * @param touchHelper target helper
     */
    abstract public void setTouchHelper(@NonNull ItemTouchHelper touchHelper);

    /**
     * Sets target view holder types that should be draggable.
     * @param viewHolderTypes class types
     */
    abstract public void setViewHolderTypes(@NonNull List<Class<? extends SmartViewHolder>> viewHolderTypes);

    /**
     * Sets the drag/move listener
     * @param onItemMovedListener listener
     */
    abstract public void setOnItemMovedListener(@NonNull OnItemMovedListener onItemMovedListener);
}
