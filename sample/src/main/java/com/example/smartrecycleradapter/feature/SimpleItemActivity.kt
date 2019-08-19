package com.example.smartrecycleradapter.feature

import android.os.Bundle
import com.example.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class SimpleItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Simple Item Sample";

        val items = (0..100).toList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}
