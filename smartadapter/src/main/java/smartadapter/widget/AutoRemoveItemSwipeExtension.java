package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.viewholder.SmartAdapterHolder;

/**
 * Automatically removes an item in {@link SmartRecyclerAdapter} when swiped.
 *
 * @see BasicSwipeExtension
 * @see SmartAdapterHolder
 */
public class AutoRemoveItemSwipeExtension extends BasicSwipeExtension implements SmartAdapterHolder {

    private SmartRecyclerAdapter smartRecyclerAdapter;

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
         super.onSwiped(viewHolder, direction);
         smartRecyclerAdapter.removeItem(viewHolder.getAdapterPosition());
    }

    @Override
    public void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }
}
