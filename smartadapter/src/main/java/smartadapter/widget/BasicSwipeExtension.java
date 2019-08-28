package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import smartadapter.listener.OnItemSwipedListener;
import smartadapter.viewholder.SmartViewHolder;

/**
 * The basic implementation of {@link SwipeExtension} that {@link SwipeExtensionBuilder} by default sets.
 *
 * @see SwipeExtension
 */
public class BasicSwipeExtension extends SwipeExtension {

    private boolean longPressDragEnabled;
    private int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    private List<Class<? extends SmartViewHolder>> viewHolderTypes = Collections.singletonList(SmartViewHolder.class);
    private OnItemSwipedListener onItemSwipedListener = (oldViewHolder, targetViewHolder) -> {}; // Noop

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = 0;
        for (Class<? extends SmartViewHolder> viewHolderType : viewHolderTypes) {
            if (viewHolderType.isAssignableFrom(viewHolder.getClass())) {
                swipeFlags = this.swipeFlags;
                break;
            }
        }
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        onItemSwipedListener.onSwiped(viewHolder, direction);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return longPressDragEnabled;
    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX,
                            float dY,
                            int actionState,
                            boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float alpha = 1 - (Math.abs(dX) / recyclerView.getWidth());
            viewHolder.itemView.setAlpha(alpha);
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void setSwipeFlags(int swipeFlags) {
        this.swipeFlags = swipeFlags;
    }

    @Override
    public void setLongPressDragEnabled(boolean longPressDragEnabled) {
        this.longPressDragEnabled = longPressDragEnabled;
    }

    @Override
    public void setViewHolderTypes(@NonNull List<Class<? extends SmartViewHolder>> viewHolderTypes) {
        this.viewHolderTypes = viewHolderTypes;
    }

    @Override
    public void setOnItemSwipedListener(@NonNull OnItemSwipedListener onItemSwipedListener) {
        this.onItemSwipedListener = onItemSwipedListener;
    }
}
