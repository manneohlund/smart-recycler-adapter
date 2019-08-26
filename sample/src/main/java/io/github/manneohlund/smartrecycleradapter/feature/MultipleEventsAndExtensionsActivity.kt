package io.github.manneohlund.smartrecycleradapter.feature

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.manneohlund.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnItemSwipedListener
import smartadapter.widget.AutoDragAndDropExtension
import smartadapter.widget.AutoRemoveItemSwipeExtension
import smartadapter.widget.DragAndDropExtensionBuilder
import smartadapter.widget.SwipeExtensionBuilder


/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class MultipleEventsAndExtensionsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Drag & Drop, Swipe, ViewEvents";

        val items = (0..50).toMutableList()

        smartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .addViewEventListener(OnItemClickListener {
                    view, actionId, position ->
                    Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .addViewEventListener(OnItemLongClickListener {
                    view, actionId, position ->
                    Toast.makeText(applicationContext, "onLongClick $position", Toast.LENGTH_SHORT).show()
                })
                .addExtensionBuilder(DragAndDropExtensionBuilder(AutoDragAndDropExtension())
                        .setDragFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN)
                        .setLongPressDragEnabled(true)
                        .setOnItemMovedListener { oldViewHolder, targetViewHolder ->
                            Toast.makeText(applicationContext, "onItemMoved from ${oldViewHolder.adapterPosition} to ${targetViewHolder.adapterPosition}", Toast.LENGTH_SHORT).show()
                        })
                .addExtensionBuilder(SwipeExtensionBuilder(AutoRemoveItemSwipeExtension())
                        .setSwipeFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                        .setOnItemSwipedListener(OnItemSwipedListener { viewHolder, direction ->
                            showToast(viewHolder, direction)
                        }))
                .into(recyclerView)
    }

    val showToast = { viewHolder: RecyclerView.ViewHolder, direction: Int ->
        val directionStr = when(direction) {
            ItemTouchHelper.LEFT -> "Left"
            ItemTouchHelper.RIGHT -> "Right"
            else -> "??"
        }
        Toast.makeText(applicationContext, "onItemSwiped @ ${viewHolder.adapterPosition}, $directionStr", Toast.LENGTH_SHORT).show()
    }
}
