package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartrecycleradapter.utils.runDelayed

class EndlessScrollLoadMoreButtonActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Endless Scroll Load More"

        var itemCount = 50
        val items = (0..itemCount).toMutableList()

        val smartAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleItemViewHolder::class)
            .into(recyclerView)

        smartAdapter.autoLoadMoreEnabled = false

        smartAdapter.onLoadMoreListener = {
            runDelayed {
                smartAdapter.addItems((itemCount + 1..itemCount + 20).toList())
                itemCount += 20
            }
        }
    }
}
