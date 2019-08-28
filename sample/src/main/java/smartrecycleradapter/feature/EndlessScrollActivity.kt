package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

class EndlessScrollActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Endless Scroll Sample";

        var itemCount = 50
        val items: List<Int> = (0..itemCount).toList()

        val smartAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .into(recyclerView)

        smartAdapter.setOnLoadMoreListener {
            Handler().postDelayed({
                smartAdapter.addItems((itemCount+1..itemCount+50).toList())
                itemCount += 50
            }, 800)
        }
    }
}
