package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.viewholder.DraggableViewHolder;
import smartadapter.viewholder.SmartAdapterHolder;

/**
 * Automatically moves an item in {@link SmartRecyclerAdapter} dragged and dropped.
 *
 * @see BasicDragAndDropExtension
 * @see SmartAdapterHolder
 */
public class AutoDragAndDropExtension extends BasicDragAndDropExtension implements SmartAdapterHolder {

    private SmartRecyclerAdapter smartRecyclerAdapter;

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        boolean moved = super.onMove(recyclerView, viewHolder, target);
        if (moved) {
            int oldPosition = viewHolder.getAdapterPosition();
            int newPosition = target.getAdapterPosition();
            Object targetItem = smartRecyclerAdapter.getItems().get(oldPosition);
            smartRecyclerAdapter.getItems().remove(oldPosition);
            smartRecyclerAdapter.getItems().add(newPosition, targetItem);
            smartRecyclerAdapter.notifyItemMoved(oldPosition, newPosition);
        }
        return moved;
    }

    /**
     * If isLongPressDragEnabled returns false this extension will try to find {@link DraggableViewHolder}s
     * and set {@link android.view.View.OnTouchListener} on a draggable view that {@link DraggableViewHolder} returns via
     * {@link DraggableViewHolder#getDraggableView()}.
     *
     * @see DraggableViewHolder
     * @param recyclerView target RecyclerView
     */
    @SuppressLint("ClickableViewAccessibility")
    protected void setupDragAndDrop(@NonNull RecyclerView recyclerView) {
        super.setupDragAndDrop(recyclerView);

        if (!isLongPressDragEnabled()) {
            smartRecyclerAdapter.addOnViewAttachedToWindowListener(viewHolder -> {
                if (viewHolder instanceof DraggableViewHolder && !draggableViews.contains(viewHolder)) {
                    draggableViews.add(viewHolder);
                    ((DraggableViewHolder) viewHolder).getDraggableView()
                            .setOnTouchListener((v, event) -> {
                                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                                    touchHelper.startDrag(viewHolder);
                                }
                                return false;
                            });
                }
            });
        }
    }

    @Override
    public void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }
}
