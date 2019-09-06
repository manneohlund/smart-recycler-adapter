package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

/**
 * Listener for drag and drop callbacks from extensions of [smartadapter.widget.DragAndDropExtension].
 */
interface OnItemMovedListener {

    /**
     * Notified from [smartadapter.widget.DragAndDropExtension.onMove]
     * with old and new view holder that should switch places.
     * @param oldViewHolder from view holder
     * @param targetViewHolder to view holder
     */
    fun onMoved(oldViewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder)
}

inline fun onItemMovedListener(crossinline onMoved: (
        oldViewHolder: RecyclerView.ViewHolder,
        targetViewHolder: RecyclerView.ViewHolder) -> Unit) = object : OnItemMovedListener {
    override fun onMoved(oldViewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder) {
        onMoved(oldViewHolder, targetViewHolder)
    }
}