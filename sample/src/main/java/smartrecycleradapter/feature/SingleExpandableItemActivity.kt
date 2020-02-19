package smartrecycleradapter.feature

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewId
import smartadapter.listener.OnClick
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
                .addViewEventListener(OnSingleItemExpandedListener { view, smartRecyclerAdapter, position ->
                    Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}

var singleExpandedStateHolder = SingleSelectionStateHolder()

class OnSingleItemExpandedListener(
    override val selectionStateHolder: SelectionStateHolder = singleExpandedStateHolder,
    override val viewId: ViewId = R.id.itemTitle,
    override val listener: OnClick
) : OnItemSelectedListener

