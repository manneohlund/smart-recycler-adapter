package io.github.manneohlund.smartrecycleradapter.feature

import android.os.Bundle
import android.widget.Toast
import io.github.manneohlund.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class SimpleItemOnClickOnLongClickActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "onClick onLongClick Sample"

        val items = (0..100).toList()

        SmartRecyclerAdapter
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
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}
