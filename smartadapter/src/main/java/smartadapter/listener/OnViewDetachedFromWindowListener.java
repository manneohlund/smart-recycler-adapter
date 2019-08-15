package smartadapter.listener;

/*
 * Created by Manne Öhlund on 03/10/17.
 * Copyright © 2017. All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.viewholder.SmartViewHolder;

/**
 * Listener for when a view created by this adapter has been detached from its window.
 *
 * <p>Invoked from {@link smartadapter.SmartRecyclerAdapter#onViewDetachedFromWindow(SmartViewHolder)} and should be implemented in a {@link SmartViewHolder} extension.</p>
 *
 * @see RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)
 */
public interface OnViewDetachedFromWindowListener {

    /**
     * Called when a view created by the adapter has been detached from its window.
     * @param viewHolder target ViewHolder
     */
    void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder);
}
