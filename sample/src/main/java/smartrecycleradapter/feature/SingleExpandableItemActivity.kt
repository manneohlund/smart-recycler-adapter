package smartrecycleradapter.feature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartadapter.state.SingleSelectionStateHolder
import smartrecycleradapter.R

/*
 * Created by Manne Öhlund on 2019-08-23.
 * Copyright (c) All rights reserved.
 */

class SingleExpandableItemActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single Expandable Item"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
                .items(items)
                .map(Integer::class, SimpleExpandableItemViewHolder::class)
                .addViewEventListener(object : OnSingleItemExpandedListener {
                    override fun onViewEvent(view: View, viewEventId: Int, position: Int) {
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}

var singleExpandedStateHolder = SingleSelectionStateHolder()

interface OnSingleItemExpandedListener : OnItemSelectedListener {

    override val selectionStateHolder: SelectionStateHolder
        get() = singleExpandedStateHolder

    override val viewId: Int
        get() = R.id.itemTitle
}
