package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.SmartRecyclerAdapterExtensionBuilder;

public class SwipeExtension extends ItemTouchHelper.Callback {

    private SmartRecyclerAdapter smartRecyclerAdapter;

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        smartRecyclerAdapter.getItems().remove(viewHolder.getAdapterPosition());
        smartRecyclerAdapter.smartNotifyItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c,
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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    /**
     * Builder for {@link SwipeExtension} that is build from {@link smartadapter.internal.factory.SmartRecyclerAdapterExtensionFactory}.
     */
    public static class SwipeExtensionBuilder implements SmartRecyclerAdapterExtensionBuilder<SwipeExtensionBuilder> {

        private SmartRecyclerAdapter smartRecyclerAdapter;
        private RecyclerView recyclerView;

        @Override
        public SwipeExtensionBuilder setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
            this.smartRecyclerAdapter = smartRecyclerAdapter;
            return this;
        }

        @Override
        public SwipeExtensionBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

        @Override
        public void build() {
            SwipeExtension dragAndDropExtension = new SwipeExtension();
            dragAndDropExtension.setSmartRecyclerAdapter(smartRecyclerAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(dragAndDropExtension);
            touchHelper.attachToRecyclerView(recyclerView);
        }
    }
}
