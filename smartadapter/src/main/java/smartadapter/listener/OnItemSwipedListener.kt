package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

typealias Direction = Int

/**
 * Listener for swipe callbacks from extensions of [smartadapter.widget.SwipeExtension].
 */
interface OnItemSwipedListener {

    /**
     * Notified from [smartadapter.widget.DragAndDropExtension.onSwiped]
     * with target view holder and swipe direction.
     * @param viewHolder target view holder
     * @param direction direction the item was swiped
     */
    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Direction)
}

inline fun onItemSwipedListener(crossinline onSwiped: (
        viewHolder: RecyclerView.ViewHolder,
        direction: Direction) -> Unit) = object : OnItemSwipedListener {
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Direction) {
        onSwiped(viewHolder, direction)
    }
}