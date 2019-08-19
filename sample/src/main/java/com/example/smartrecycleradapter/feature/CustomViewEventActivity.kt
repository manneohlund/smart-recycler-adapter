package com.example.smartrecycleradapter.feature

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
import com.example.smartrecycleradapter.R
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewEventListenerHolder
import smartadapter.viewholder.SmartViewHolder

const val CUSTOM_EVENT = R.id.custom_event

class CustomViewEventActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Custom View Event Sample"

        val items = (0..100).toList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .addViewEventListener { view, actionId, position ->
                    if (actionId == CUSTOM_EVENT) {
                        Toast.makeText(applicationContext, "CUSTOM_EVENT $position", Toast.LENGTH_SHORT).show()
                    }
                }
                .into<SmartRecyclerAdapter>(recyclerView)
    }

    open class SimpleItemViewHolder(parentView: View) : SmartViewHolder<Int>(
            LayoutInflater.from(parentView.context)
                    .inflate(R.layout.simple_item, parentView as ViewGroup, false)),
            SmartViewEventListenerHolder {

        private lateinit var viewEventListener: OnViewEventListener

        override fun setOnViewEventListener(viewEventListener: OnViewEventListener) {
            this.viewEventListener = viewEventListener
        }

        init {
            itemView.setOnClickListener {view ->
                viewEventListener.onViewEvent(view, CUSTOM_EVENT, adapterPosition)
            }
        }

        override fun bind(index: Int?) {
            (itemView as TextView).text = "Item $index"
        }
    }
}
