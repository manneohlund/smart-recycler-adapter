package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright © 2019. All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView
import smartadapter.viewholder.SmartViewHolder

/**
 * Listener for when a view created by the adapter has been attached to a window.
 *
 * Invoked from [smartadapter.SmartRecyclerAdapter.onViewAttachedToWindow] and should be implemented in a [SmartViewHolder] extension.
 *
 * @see RecyclerView.Adapter.onViewAttachedToWindow
 */
interface OnViewAttachedToWindowListener {
    /**
     * Called when a view created by the adapter has been attached to a window.
     * @param viewHolder target ViewHolder
     */
    fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder)
}

/**
 * Helper method to provide lambda call to interface instances of [OnViewAttachedToWindowListener].
 */
inline fun onViewAttachedToWindowListener(crossinline listener: (viewHolder: RecyclerView.ViewHolder) -> Unit) =
    object : OnViewAttachedToWindowListener {
        override fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) {
            listener(viewHolder)
        }
    }
