package smartrecycleradapter.feature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

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
                .map(Integer::class, SimpleItemViewHolder::class)
                .addViewEventListener(object : OnItemClickListener {
                    override fun onViewEvent(view: View, viewEventId: Int, position: Int) {
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .addViewEventListener(object : OnItemLongClickListener {
                    override fun onViewEvent(view: View, viewEventId: Int, position: Int) {
                        Toast.makeText(applicationContext, "onLongClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}
