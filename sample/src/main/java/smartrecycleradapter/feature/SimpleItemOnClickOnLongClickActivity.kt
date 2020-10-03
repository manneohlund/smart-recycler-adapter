package smartrecycleradapter.feature

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnLongClickEventListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewholder.OnItemClickEventListener
import smartadapter.viewholder.OnItemLongClickEventListener
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartrecycleradapter.utils.showToast

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class SimpleItemOnClickOnLongClickActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "onClick onLongClick Sample"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleEventListenerViewHolder::class)
            .add(OnClickEventListener {
                showToast("onItemClick ${it.position}")
            })
            .add(OnClickEventListener {
                showToast("onItemClick ${it.position}")
            })
            .add(OnLongClickEventListener {
                showToast("onItemLongClick ${it.position}")
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }

    class SimpleEventListenerViewHolder(parentView: ViewGroup) : SimpleItemViewHolder(parentView),
        OnItemClickEventListener,
        OnItemLongClickEventListener {

        override fun onViewEvent(event: ViewEvent.OnClick) {
            Toast.makeText(itemView.context, "SimpleEventListenerViewHolder ${event::class.simpleName} intercept", Toast.LENGTH_SHORT).show()
        }

        override fun onViewEvent(event: ViewEvent.OnLongClick) {
            Toast.makeText(itemView.context, "SimpleEventListenerViewHolder ${event::class.simpleName} intercept", Toast.LENGTH_SHORT).show()
        }
    }
}
