package smartrecycleradapter.feature

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.smartNotifyItemChange
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class SimpleItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Simple Item Sample"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class, SimpleItemViewHolder::class)
                .into<SmartRecyclerAdapter>(recyclerView).apply {
                    // The part of code ables change of item in positions with 500mls interval
                    Thread {
                        items.forEachIndexed { index, i ->
                            recyclerView.post { smartNotifyItemChange(index, i * index) }
                            Thread.sleep(500)
                        }
                    }.start()
                }


    }
}
