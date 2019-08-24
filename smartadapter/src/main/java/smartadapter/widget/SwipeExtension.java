package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.List;

import smartadapter.listener.OnItemSwipedListener;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Defines basic functionality of the DragAndDropExtension.
 */
public abstract class SwipeExtension extends ItemTouchHelper.Callback {

    /**
     * Sets target swipe flags.
     * @see ItemTouchHelper#LEFT
     * @see ItemTouchHelper#RIGHT
     * @param swipeFlags flags
     */
    abstract public void setSwipeFlags(int swipeFlags);

    /**
     * Defines if item should be draggable after long press.
     * @param longPressDragEnabled should drag be enabled
     */
    abstract public void setLongPressDragEnabled(boolean longPressDragEnabled);

    /**
     * Sets target view holder types that should be draggable.
     * @param viewHolderTypes class types
     */
    abstract public void setViewHolderTypes(@NonNull List<Class<? extends SmartViewHolder>> viewHolderTypes);

    /**
     * Sets the swipe listener.
     * @param onItemSwipedListener listener
     */
    abstract void setOnItemSwipedListener(@NonNull OnItemSwipedListener onItemSwipedListener);
}
