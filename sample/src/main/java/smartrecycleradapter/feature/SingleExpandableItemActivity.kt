package smartrecycleradapter.feature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SingleSelectionStateHolder
import smartrecycleradapter.R

/*
 * Created by Manne Öhlund on 2019-08-23.
 * Copyright (c) All rights reserved.
 */

class SingleExpandableItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single Expandable Item";

        val items = (0..100).toList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleExpandableItemViewHolder::class.java)
                .addViewEventListener(object : OnSingleItemExpandedListener {
                    override fun onViewEvent(view: View, actionId: Int, position: Int) {
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}

var singleExpandedStateHolder = SingleSelectionStateHolder()

interface OnSingleItemExpandedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = singleExpandedStateHolder

    @JvmDefault
    override fun getViewId() = R.id.itemTitle
}