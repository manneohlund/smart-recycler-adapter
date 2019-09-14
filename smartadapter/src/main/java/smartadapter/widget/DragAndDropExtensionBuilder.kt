package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartExtensionBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.listener.OnItemMovedListener
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder

/**
 * Builder for DragAndDropExtensions. Default constructor sets [BasicDragAndDropExtension].
 */
class DragAndDropExtensionBuilder :
    SmartExtensionBuilder<DragAndDropExtension> {

    private var dragAndDropExtension: DragAndDropExtension
    var dragFlags: Int = 0
    var viewHolderTypes: List<SmartViewHolderType> = listOf(SmartViewHolder::class)
    var longPressDragEnabled: Boolean = false
    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    override lateinit var recyclerView: RecyclerView
    var onItemMovedListener: OnItemMovedListener = { _, _ -> } // Noop

    constructor() {
        dragAndDropExtension = BasicDragAndDropExtension()
    }

    constructor(dragAndDropExtension: DragAndDropExtension) {
        this.dragAndDropExtension = dragAndDropExtension
    }

    override fun build(): DragAndDropExtension {
        if (dragAndDropExtension is SmartAdapterHolder) {
            (dragAndDropExtension as SmartAdapterHolder).smartRecyclerAdapter = smartRecyclerAdapter
        }
        dragAndDropExtension.longPressDragEnabled = longPressDragEnabled
        dragAndDropExtension.dragFlags = dragFlags
        dragAndDropExtension.viewHolderTypes = viewHolderTypes
        dragAndDropExtension.onItemMovedListener = onItemMovedListener
        val touchHelper = ItemTouchHelper(dragAndDropExtension)
        dragAndDropExtension.touchHelper = touchHelper
        touchHelper.attachToRecyclerView(recyclerView)
        dragAndDropExtension.setupDragAndDrop(recyclerView)
        return dragAndDropExtension
    }
}
