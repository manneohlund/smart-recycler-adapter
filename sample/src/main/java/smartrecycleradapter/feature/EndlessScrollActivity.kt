package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.listener.onLoadMoreListener
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

class EndlessScrollActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Endless Scroll Sample"

        var itemCount = 50
        val items: MutableList<Any> = (0..itemCount).toMutableList()

        val smartAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleItemViewHolder::class)
            .into(recyclerView)

        smartAdapter.setOnLoadMoreListener(onLoadMoreListener {
            Handler().postDelayed({
                smartAdapter.addItems((itemCount + 1..itemCount + 50).toList())
                itemCount += 50
            }, 800)
        })
    }
}
