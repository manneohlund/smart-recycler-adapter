package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.listener.OnClick
import smartadapter.listener.OnItemLongClickSelectedListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnLongClick
import smartadapter.state.SelectionStateHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.CopyrightModel
import smartrecycleradapter.viewholder.CopyrightViewHolder

class MultipleViewTypesResolverActivity : BaseSampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Types Resolver"

        val items = (0..100).toMutableList()

        val onCheckBoxItemSelectedListener = OnSimpleCheckBoxItemSelectedListener { view, smartRecyclerAdapter, position ->
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Checkbox click %d\n" +
                                "%d selected items",
                        position,
                        sharedMultipleTypesStateHolder.selectedItemsCount
                    ),
                    Toast.LENGTH_LONG
                ).show()
        }

        val onSwitchItemSelectedListener = OnSimpleSwitchItemSelectedListener { view, smartRecyclerAdapter, position ->
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Item click %d\n" +
                                "%d selected items",
                        position,
                        sharedMultipleTypesStateHolder.selectedItemsCount
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }

        SmartRecyclerAdapter
            .items(items)
            .map(CopyrightModel::class, CopyrightViewHolder::class)
            .addViewEventListener(OnSimpleItemSelectedListener { view, smartRecyclerAdapter, position ->
                Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
            })
            .addViewEventListener(onCheckBoxItemSelectedListener)
            .addViewEventListener(onSwitchItemSelectedListener)
            .setViewTypeResolver { item, position ->
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

class OnSimpleItemSelectedListener(
    override val selectionStateHolder: SelectionStateHolder = sharedMultipleTypesStateHolder,
    override val viewHolderType: SmartViewHolderType  = SimpleSelectableItemViewHolder::class,
    override val listener: OnLongClick
) : OnItemLongClickSelectedListener

class OnSimpleCheckBoxItemSelectedListener(
    override val selectionStateHolder: SelectionStateHolder = sharedMultipleTypesStateHolder,
    override val viewHolderType: SmartViewHolderType = SimpleSelectableCheckBoxViewHolder::class,
    override val viewId: ViewId = R.id.checkBox,
    override val listener: OnClick
) : OnItemSelectedListener

class OnSimpleSwitchItemSelectedListener(
    override val selectionStateHolder: SelectionStateHolder = sharedMultipleTypesStateHolder,
    override val viewHolderType: SmartViewHolderType = SimpleSelectableSwitchViewHolder::class,
    override val viewId: ViewId = R.id.switchButton,
    override val listener: OnClick
) : OnItemSelectedListener
