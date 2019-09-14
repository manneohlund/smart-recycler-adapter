package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewEventId
import smartadapter.listener.OnViewEventListener
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
            .addViewEventListener(object : OnViewEventListener {
                override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
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

    open class SimpleItemViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
            .inflate(R.layout.simple_item, parentView as ViewGroup, false)
    ),
        ViewEventListenerHolder {

        private lateinit var viewEventListener: OnViewEventListener

        override fun setOnViewEventListener(viewEventListener: OnViewEventListener) {
            this.viewEventListener = viewEventListener
        }

        init {
            itemView.setOnClickListener { view ->
                viewEventListener.onViewEvent(view, CUSTOM_EVENT, adapterPosition)
            }
        }

        override fun bind(item: Int) {
            (itemView as TextView).text = "Item $item"
        }
    }
}
