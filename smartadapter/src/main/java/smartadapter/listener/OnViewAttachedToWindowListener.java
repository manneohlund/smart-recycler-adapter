package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright © 2019. All rights reserved.
 */

import android.support.v7.widget.RecyclerView;

import smartadapter.viewholder.SmartViewHolder;

/**
 * Listener for when a view created by the adapter has been attached to a window.
 *
 * <p>Invoked from {@link smartadapter.SmartRecyclerAdapterImpl#onViewAttachedToWindow(SmartViewHolder)} and should be implemented in a {@link SmartViewHolder} extension.</p>
 *
 * @see android.support.v7.widget.RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)
 */
public interface OnViewAttachedToWindowListener {

    /**
     * Called when a view created by the adapter has been attached to a window.
     */
    void onViewAttachedToWindow();
}
