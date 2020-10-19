package smartrecycleradapter.feature

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.dragdrop.AutoDragAndDropBinder
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnLongClickEventListener
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartrecycleradapter.utils.showToast

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
            .add(OnClickEventListener {
                showToast("onClick ${it.position}")
            })
            .add(OnLongClickEventListener {
                showToast("onLongClick ${it.position}")
            })
            .add(AutoDragAndDropBinder(longPressDragEnabled = true) { event ->
                supportActionBar?.subtitle =
                    "onItemMoved from ${event.viewHolder.adapterPosition} to ${event.targetViewHolder.adapterPosition}"
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }
}
