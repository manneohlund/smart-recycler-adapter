package com.example.smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.CopyrightModel
import com.example.smartrecycleradapter.viewholder.CopyrightViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder

class MultipleViewTypesResolverActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Types Resolver";

        val items = (0..100).toList()

        val onCheckBoxItemSelectedListener = object : OnSimpleCheckBoxItemSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Checkbox click %d\n" +
                                "%d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount),
                        Toast.LENGTH_LONG).show()
            }
        }

        val onSwitchItemSelectedListener = object : OnSimpleSwitchItemSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Item click %d\n" +
                                "%d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount),
                        Toast.LENGTH_LONG).show()
            }
        }

        SmartRecyclerAdapter
                .items(items)
                .map(CopyrightModel::class.java, CopyrightViewHolder::class.java)
                .addViewEventListener(OnItemClickListener {
                    view, actionId, position ->
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .addViewEventListener(onCheckBoxItemSelectedListener)
                .addViewEventListener(onSwitchItemSelectedListener)
                .setViewTypeResolver { item, position ->
                    when {
                        position % 3 == 1 -> SimpleSelectableCheckBoxViewHolder::class.java
                        position % 3 == 2 -> SimpleSelectableSwitchViewHolder::class.java
                        else -> SimpleSelectableItemViewHolder::class.java
                    }
                }
                .into<SmartRecyclerAdapter>(recyclerView)
    }
}

var sharedMultipleTypesStateHolder: SelectionStateHolder = SelectionStateHolder()

interface OnSimpleCheckBoxItemSelectedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = sharedMultipleTypesStateHolder

    @JvmDefault
    override fun getViewHolderType() = SimpleSelectableCheckBoxViewHolder::class.java

    @JvmDefault
    override fun getViewId() = R.id.checkBox
}

interface OnSimpleSwitchItemSelectedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = sharedMultipleTypesStateHolder

    @JvmDefault
    override fun getViewHolderType() = SimpleSelectableSwitchViewHolder::class.java

    @JvmDefault
    override fun getViewId() = R.id.switchButton
}