package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnSmartViewEvent
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import smartrecycleradapter.R

const val CUSTOM_EVENT = R.id.custom_event

class CustomViewEventActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Custom View Event Sample"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleItemViewHolder::class)
            .addViewEventListener(object : OnViewEventListener<OnSmartViewEvent> {
                override val listener: OnSmartViewEvent = OnSmartViewEvent { view, viewEventId, adapter, position ->
                    if (viewEventId == CUSTOM_EVENT) {
                        Toast.makeText(
                            applicationContext,
                            "CUSTOM_EVENT $position",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }

    open class SimpleItemViewHolder(parentView: ViewGroup) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context).inflate(R.layout.simple_item, parentView, false)
    ), ViewEventListenerHolder<OnSmartViewEvent>, SmartAdapterHolder {

        override var viewEventListener: OnSmartViewEvent? = null
        override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

        init {
            itemView.setOnClickListener { view ->
                viewEventListener?.event?.invoke(view, CUSTOM_EVENT, smartRecyclerAdapter, adapterPosition)
            }
        }

        override fun bind(item: Int) {
            (itemView as TextView).text = "Item $item"
        }
    }
}
