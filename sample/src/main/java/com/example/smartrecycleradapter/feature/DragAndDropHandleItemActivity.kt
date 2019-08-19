package com.example.smartrecycleradapter.feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.smartrecycleradapter.R
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.viewholder.DraggableViewHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.widget.AutoDragAndDropExtension
import smartadapter.widget.DragAndDropExtensionBuilder


/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class DragAndDropHandleItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Drag & Drop handle";

        val items = (0..50).toMutableList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleDragHandleItemViewHolder::class.java)
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
                        .setOnItemMovedListener { oldViewHolder, targetViewHolder ->
                            Log.i(DragAndDropHandleItemActivity::class.java.name, "onItemMoved from ${oldViewHolder.adapterPosition} to ${targetViewHolder.adapterPosition}")
                        })
                .into<SmartRecyclerAdapter>(recyclerView)
    }

    class SimpleDragHandleItemViewHolder(parentView: View) : SmartViewHolder<Int>(
            LayoutInflater.from(parentView.context)
                    .inflate(R.layout.simple_drag_handle_item, parentView as ViewGroup, false)), DraggableViewHolder {

        private var textView: TextView = itemView.findViewById(R.id.textView)
        private val dragHandle: View = itemView.findViewById(R.id.dragHandle);

        override fun getDraggableView() = dragHandle

        override fun bind(index: Int?) {
            textView.text = "Item $index"
        }
    }
}
