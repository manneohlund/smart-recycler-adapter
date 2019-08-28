package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartrecycleradapter.R

class MultiSelectSwitchItemsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;
    lateinit var onSwitchItemSelectedListener: OnSwitchItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi Switch Select"

        val items = (0..100).toList()

        onSwitchItemSelectedListener = object : OnSwitchItemSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Item click %d\n" +
                                "%d of %d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount,
                                smartRecyclerAdapter.itemCount),
                        Toast.LENGTH_LONG).show()
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleSelectableSwitchViewHolder::class.java)
                .addViewEventListener(onSwitchItemSelectedListener)
                .addViewEventListener(OnItemClickListener {
                    view, actionId, position ->
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .into(recyclerView)
    }
}

var switchStateHolder: SelectionStateHolder = SelectionStateHolder()

interface OnSwitchItemSelectedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = switchStateHolder

    @JvmDefault
    override fun getViewId() = R.id.switchButton
}