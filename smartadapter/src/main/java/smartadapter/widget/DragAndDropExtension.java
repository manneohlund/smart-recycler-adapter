package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.SmartRecyclerAdapterExtensionBuilder;
import smartadapter.viewholder.DraggableViewHolder;

public class DragAndDropExtension extends ItemTouchHelper.Callback {

    private final boolean longPressDragEnabled;
    private SmartRecyclerAdapter smartRecyclerAdapter;
    private ItemTouchHelper touchHelper;
    private HashSet<RecyclerView.ViewHolder> draggableViews = new HashSet<>();

    public DragAndDropExtension(boolean longPressDragEnabled) {
        this.longPressDragEnabled = longPressDragEnabled;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int oldPosition = viewHolder.getAdapterPosition();
        int newPosition = target.getAdapterPosition();
        Object targetItem = smartRecyclerAdapter.getItems().get(oldPosition);
        smartRecyclerAdapter.getItems().remove(oldPosition);
        smartRecyclerAdapter.getItems().add(newPosition, targetItem);
        smartRecyclerAdapter.notifyItemMoved(oldPosition, newPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // Noop
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return longPressDragEnabled;
    }

    public void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupDragAndDrop() {
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

    public static class DragAndDropBuilder implements SmartRecyclerAdapterExtensionBuilder<DragAndDropBuilder> {

        private SmartRecyclerAdapter smartRecyclerAdapter;
        private RecyclerView recyclerView;
        protected boolean longPressDragEnabled;

        public DragAndDropBuilder() {
            this(true);
        }

        public DragAndDropBuilder(boolean longPressDragEnabled) {
            this.longPressDragEnabled = longPressDragEnabled;
        }

        @Override
        public DragAndDropBuilder setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
            this.smartRecyclerAdapter = smartRecyclerAdapter;
            return this;
        }

        @Override
        public DragAndDropBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

        @Override
        public void build() {
            DragAndDropExtension dragAndDropExtension = new DragAndDropExtension(longPressDragEnabled);
            dragAndDropExtension.setSmartRecyclerAdapter(smartRecyclerAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(dragAndDropExtension);
            dragAndDropExtension.setTouchHelper(touchHelper);
            touchHelper.attachToRecyclerView(recyclerView);
            dragAndDropExtension.setupDragAndDrop();
        }
    }
}
