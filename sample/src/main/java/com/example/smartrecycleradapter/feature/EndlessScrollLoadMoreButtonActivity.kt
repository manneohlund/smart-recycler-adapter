package com.example.smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartEndlessScrollRecyclerAdapter

class EndlessScrollLoadMoreButtonActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Endless Scroll Load More";

        var itemCount = 50
        val items: List<Int> = (0..itemCount).toList()

        val smartAdapter: SmartEndlessScrollRecyclerAdapter = SmartEndlessScrollRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .into(recyclerView)

        smartAdapter.setAutoLoadMore(false)
        smartAdapter.setOnLoadMoreListener {
            Toast.makeText(applicationContext, "LoadMore", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({
                smartAdapter.addItems((itemCount+1..itemCount+50).toList())
                itemCount += 50
            }, 800)
        }
    }
}
