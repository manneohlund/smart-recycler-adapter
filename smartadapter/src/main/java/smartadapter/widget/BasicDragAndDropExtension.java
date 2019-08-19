package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import smartadapter.listener.OnItemMovedListener;
import smartadapter.viewholder.SmartViewHolder;

/**
 * The basic implementation of {@link DragAndDropExtension} that {@link DragAndDropExtensionBuilder} by default sets.
 *
 * @see DragAndDropExtension
 */
public class BasicDragAndDropExtension extends DragAndDropExtension {

    private boolean longPressDragEnabled;
    private int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    private List<Class<? extends SmartViewHolder>> viewHolderTypes = Collections.singletonList(SmartViewHolder.class);
    protected ItemTouchHelper touchHelper;
    protected HashSet<RecyclerView.ViewHolder> draggableViews = new HashSet<>();
    private OnItemMovedListener onItemMovedListener = (oldViewHolder, targetViewHolder) -> {}; // Noop

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        for (Class<? extends SmartViewHolder> viewHolderType : viewHolderTypes) {
            if (viewHolderType.isAssignableFrom(target.getClass()) && viewHolder.getClass().equals(target.getClass())) {
                onItemMovedListener.onMoved(viewHolder, target);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // Noop
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return longPressDragEnabled;
    }

    @Override
    public void setLongPressDragEnabled(boolean longPressDragEnabled) {
        this.longPressDragEnabled = longPressDragEnabled;
    }

    @Override
    public void setDragFlags(int dragFlags) {
        this.dragFlags = dragFlags;
    }

    @Override
    public void setTouchHelper(@NonNull ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setupDragAndDrop(@NonNull RecyclerView recyclerView) {
        if (dragFlags == 0) {
            int gridDragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
            int linearDragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

            dragFlags = recyclerView.getLayoutManager() instanceof GridLayoutManager
                    ? gridDragFlags
                    : linearDragFlags;
        }
    }

    @Override
    public void setViewHolderTypes(@NonNull List<Class<? extends SmartViewHolder>> viewHolderTypes) {
        this.viewHolderTypes = viewHolderTypes;
    }

    @Override
    public void setOnItemMovedListener(@NonNull OnItemMovedListener onItemMovedListener) {
        this.onItemMovedListener = onItemMovedListener;
    }
}
