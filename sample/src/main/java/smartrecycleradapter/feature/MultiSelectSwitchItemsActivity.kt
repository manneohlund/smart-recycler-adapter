package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewId
import smartadapter.listener.OnClick
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartrecycleradapter.R

class MultiSelectSwitchItemsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private lateinit var onSwitchItemSelectedListener: OnSwitchItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi Switch Select"

        val items = (0..100).toMutableList()

        onSwitchItemSelectedListener = object : OnSwitchItemSelectedListener {
            override val listener: OnClick = { view, smartRecyclerAdapter, position ->
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Item click %d\n" +
                            "%d of %d selected items",
                        position,
                        selectionStateHolder.selectedItemsCount,
                        smartRecyclerAdapter.itemCount
                    ), Toast.LENGTH_LONG
                ).show()

                supportActionBar?.subtitle =
                    "${switchStateHolder.selectedItemsCount} / ${items.size} selected"
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableSwitchViewHolder::class)
            .addViewEventListener(onSwitchItemSelectedListener)
            .addViewEventListener(object : OnItemClickListener {
                override val listener: OnClick = { view, smartRecyclerAdapter, position ->
                    Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                }
            })
            .into(recyclerView)
    }
}

var switchStateHolder: SelectionStateHolder = SelectionStateHolder()

interface OnSwitchItemSelectedListener : OnItemSelectedListener {
    override val selectionStateHolder: SelectionStateHolder
        get() = switchStateHolder

    override val viewId: ViewId
        get() = R.id.switchButton
}
