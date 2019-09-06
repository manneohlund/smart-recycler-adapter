package smartadapter.listener

/*
 * Created by Manne Öhlund on 03/10/17.
 * Copyright © 2017. All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

import smartadapter.viewholder.SmartViewHolder

/**
 * Listener for when a view created by this adapter has been detached from its window.
 *
 *
 * Invoked from [smartadapter.SmartRecyclerAdapter.onViewDetachedFromWindow] and should be implemented in a [SmartViewHolder] extension.
 *
 * @see RecyclerView.Adapter.onViewDetachedFromWindow
 */
interface OnViewDetachedFromWindowListener {

    /**
     * Called when a view created by the adapter has been detached from its window.
     * @param viewHolder target ViewHolder
     */
    fun onViewDetachedFromWindow(viewHolder: RecyclerView.ViewHolder)
}
