package smartadapter.listener;

/*
 * Created by Manne Öhlund on 03/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.support.v7.widget.RecyclerView;

import smartadapter.viewholder.SmartViewHolder;

/**
 * Listener for when a view created by this adapter has been detached from its window.
 *
 * <p>Invoked from {@link smartadapter.SmartRecyclerAdapterImpl#onViewDetachedFromWindow(SmartViewHolder)} and should be implemented in a {@link SmartViewHolder} extension.</p>
 *
 * @see android.support.v7.widget.RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)
 */
public interface OnViewDetachedFromWindowListener {

    /**
     * Called when a view created by the adapter has been detached from its window.
     */
    void onViewDetachedFromWindow();
}
