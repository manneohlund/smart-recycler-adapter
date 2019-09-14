package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartExtensionBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder

/**
 * Builder for [SwipeExtension] that is build from [smartadapter.internal.factory.SmartRecyclerAdapterExtensionFactory].
 */
class SwipeExtensionBuilder : SmartExtensionBuilder<SwipeExtension> {

    private var swipeExtension: SwipeExtension
    var swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    var longPressDragEnabled: Boolean = false
    var viewHolderTypes = listOf(SmartViewHolder::class)
    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    override lateinit var recyclerView: RecyclerView
    var onItemSwipedListener: OnItemSwipedListener = { _, _ -> } // No op

    constructor() {
        swipeExtension = BasicSwipeExtension()
    }

    constructor(swipeExtension: SwipeExtension) {
        this.swipeExtension = swipeExtension
    }

    override fun build(): SwipeExtension {
        if (swipeExtension is SmartAdapterHolder) {
            (swipeExtension as SmartAdapterHolder).smartRecyclerAdapter = smartRecyclerAdapter
        }
        swipeExtension.setSwipeFlags(swipeFlags)
        swipeExtension.isLongPressDragEnabled = longPressDragEnabled
        swipeExtension.setViewHolderTypes(viewHolderTypes)
        swipeExtension.setOnItemSwipedListener(onItemSwipedListener)
        val touchHelper = ItemTouchHelper(swipeExtension)
        touchHelper.attachToRecyclerView(recyclerView)
        return swipeExtension
    }
}
