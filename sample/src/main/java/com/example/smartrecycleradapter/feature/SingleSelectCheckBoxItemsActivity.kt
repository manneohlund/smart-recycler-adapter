package com.example.smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.smartrecycleradapter.R
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SingleSelectionStateHolder

class SingleSelectCheckBoxItemsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;
    lateinit var onCheckBoxItemSelectedListener: OnSingleCheckBoxItemSelectedListener
    var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single CheckBox Select"

        val items = (0..100).toList()

        onCheckBoxItemSelectedListener = object : OnSingleCheckBoxItemSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Item click %d\n" +
                                "%d of %d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount,
                                smartRecyclerAdapter.itemCount),
                        Toast.LENGTH_LONG).show()

                deleteMenuItem?.isVisible = onCheckBoxItemSelectedListener.selectionStateHolder.selectedItemsCount > 0
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleSelectableCheckBoxViewHolder::class.java)
                .addViewEventListener(onCheckBoxItemSelectedListener)
                .addViewEventListener(OnItemClickListener {
                    view, actionId, position ->
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .into(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete, menu)
        deleteMenuItem = menu?.findItem(R.id.delete)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.delete -> {
                onCheckBoxItemSelectedListener.selectionStateHolder.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

var singleCheckBoxStateHolder = SingleSelectionStateHolder()

interface OnSingleCheckBoxItemSelectedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = singleCheckBoxStateHolder

    @JvmDefault
    override fun getViewId() = R.id.checkBox
}