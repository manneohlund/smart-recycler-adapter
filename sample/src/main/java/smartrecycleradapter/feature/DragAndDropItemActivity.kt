package smartrecycleradapter.feature

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnLongClick
import smartadapter.widget.AutoDragAndDropExtension
import smartadapter.widget.DragAndDropExtensionBuilder
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class DragAndDropItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Drag & Drop"

        val items = (0..50).toMutableList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class, SimpleItemViewHolder::class)
                .addViewEventListener(object : OnItemLongClickListener {
                    override val listener: OnLongClick = { view, smartRecyclerAdapter, position ->
                        Toast.makeText(applicationContext, "onLongClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .addExtensionBuilder(DragAndDropExtensionBuilder(AutoDragAndDropExtension()).apply {
                    longPressDragEnabled = true
                    onItemMovedListener = { oldViewHolder, targetViewHolder ->
                        Log.i(DragAndDropItemActivity::class.java.name, "onItemMoved from ${oldViewHolder.adapterPosition} to ${targetViewHolder.adapterPosition}")
                    }
                })
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}
