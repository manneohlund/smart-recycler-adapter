package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartrecycleradapter.utils.runDelayed
import smartrecycleradapter.utils.showToast

class EndlessScrollActivity : BaseSampleActivity() {

    var itemCount = 50
    lateinit var smartAdapter: SmartEndlessScrollRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Endless Scroll Sample"

        val items: MutableList<Any> = (0..itemCount).toMutableList()

        smartAdapter = SmartEndlessScrollRecyclerAdapter
            .items(items)
            .setAutoLoadMoreEnabled(true)
            .setLoadMoreLayoutResource(R.layout.custom_load_more_view)
            .setOnLoadMoreListener { adapter, loadMoreViewHolder ->
                when {
                    itemCount < 100 -> addMoreStuff()
                    else -> disableScroll()
                }
            }
            .map(Integer::class, SimpleItemViewHolder::class)
            .into(recyclerView)
    }

    private fun addMoreStuff() {
        runDelayed {
            smartAdapter.addItems((itemCount + 1..itemCount + 20).toList())
            itemCount += 20
        }
    }

    private fun disableScroll() {
        runDelayed {
            smartAdapter.isEndlessScrollEnabled = false
            showToast("No more items to load")
        }
    }
}
