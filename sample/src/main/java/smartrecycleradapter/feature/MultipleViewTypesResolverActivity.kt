package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.*
import smartadapter.listener.OnItemLongClickSelectedListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.CopyrightModel
import smartrecycleradapter.viewholder.CopyrightViewHolder

class MultipleViewTypesResolverActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Types Resolver"

        val items = (0..100).toMutableList()

        val onCheckBoxItemSelectedListener = object : OnSimpleCheckBoxItemSelectedListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Checkbox click %d\n" +
                                "%d selected items",
                        position,
                        selectionStateHolder.selectedItemsCount
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val onSwitchItemSelectedListener = object : OnSimpleSwitchItemSelectedListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Item click %d\n" +
                                "%d selected items",
                        position,
                        selectionStateHolder.selectedItemsCount
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        SmartRecyclerAdapter
            .items(items)
            .map(CopyrightModel::class, CopyrightViewHolder::class)
            .addViewEventListener(object : OnSimpleItemSelectedListener {
                override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                    Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .addViewEventListener(onCheckBoxItemSelectedListener)
            .addViewEventListener(onSwitchItemSelectedListener)
            .setViewTypeResolver { _, position ->
                when {
                        position % 3 == 1 -> SimpleSelectableCheckBoxViewHolder::class
                        position % 3 == 2 -> SimpleSelectableSwitchViewHolder::class
                        else -> SimpleSelectableItemViewHolder::class
                    }
                }
            .into<SmartRecyclerAdapter>(recyclerView)
    }
}

var sharedMultipleTypesStateHolder = SelectionStateHolder()

interface OnSimpleItemSelectedListener : OnItemLongClickSelectedListener {
    override val selectionStateHolder: SelectionStateHolder
        get() = sharedMultipleTypesStateHolder

    override val viewHolderType: SmartViewHolderType
        get() = SimpleSelectableItemViewHolder::class
}

interface OnSimpleCheckBoxItemSelectedListener : OnItemSelectedListener {
    override val selectionStateHolder: SelectionStateHolder
        get() = sharedMultipleTypesStateHolder

    override val viewHolderType: SmartViewHolderType
        get() = SimpleSelectableCheckBoxViewHolder::class

    override val viewId: ViewId
        get() = R.id.checkBox
}

interface OnSimpleSwitchItemSelectedListener : OnItemSelectedListener {
    override val selectionStateHolder: SelectionStateHolder
        get() = sharedMultipleTypesStateHolder

    override val viewHolderType: SmartViewHolderType
        get() = SimpleSelectableSwitchViewHolder::class

    override val viewId: ViewId
        get() = R.id.switchButton
}
