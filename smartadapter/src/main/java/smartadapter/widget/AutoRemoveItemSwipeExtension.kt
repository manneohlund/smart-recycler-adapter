package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartAdapterHolder

/**
 * Automatically removes an item in [SmartRecyclerAdapter] when swiped.
 *
 * @see BasicSwipeExtension
 *
 * @see SmartAdapterHolder
 */
class AutoRemoveItemSwipeExtension : BasicSwipeExtension(), SmartAdapterHolder {

    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        super.onSwiped(viewHolder, direction)
        smartRecyclerAdapter.removeItem(viewHolder.adapterPosition)
    }
}
