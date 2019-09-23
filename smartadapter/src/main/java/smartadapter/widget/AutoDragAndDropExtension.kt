package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.onViewAttachedToWindowListener
import smartadapter.viewholder.DraggableViewHolder
import smartadapter.viewholder.SmartAdapterHolder
import java.util.*

/**
 * Automatically moves an item in [SmartRecyclerAdapter] dragged and dropped.
 *
 * @see BasicDragAndDropExtension
 * @see SmartAdapterHolder
 */
class AutoDragAndDropExtension : BasicDragAndDropExtension(), SmartAdapterHolder {

    override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
    private val draggableViews = HashSet<RecyclerView.ViewHolder>()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val moved = super.onMove(recyclerView, viewHolder, target)
        if (moved) {
            val oldPosition = viewHolder.adapterPosition
            val newPosition = target.adapterPosition
            with (smartRecyclerAdapter!!) {
                val targetItem = getItems()[oldPosition]
                getItems().removeAt(oldPosition)
                getItems().add(newPosition, targetItem)
                notifyItemMoved(oldPosition, newPosition)
            }
        }
        return moved
    }

    /**
     * If isLongPressDragEnabled returns false this extension will try to find [DraggableViewHolder]s
     * and set [android.view.View.OnTouchListener] on a draggable view that [DraggableViewHolder] returns via
     * [DraggableViewHolder.getDraggableView].
     *
     * @see DraggableViewHolder
     *
     * @param recyclerView target RecyclerView
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun setupDragAndDrop(recyclerView: RecyclerView) {
        super.setupDragAndDrop(recyclerView)

        if (!isLongPressDragEnabled) {
            smartRecyclerAdapter?.addOnViewAttachedToWindowListener(onViewAttachedToWindowListener { viewHolder ->
                if (viewHolder is DraggableViewHolder && !draggableViews.contains(viewHolder)) {
                    draggableViews.add(viewHolder)
                    (viewHolder as DraggableViewHolder).draggableView
                        .setOnTouchListener { _, event ->
                            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                                touchHelper?.startDrag(viewHolder)
                            }
                            false
                        }
                }
            })
        }
    }
}
