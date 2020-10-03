package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnMultiItemCheckListener
import smartadapter.viewevent.listener.OnMultiItemSelectListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewmodel.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.models.CopyrightModel
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.CopyrightViewHolder
import smartrecycleradapter.viewholder.SimpleSelectableCheckBoxViewHolder
import smartrecycleradapter.viewholder.SimpleSelectableItemViewHolder
import smartrecycleradapter.viewholder.SimpleSelectableSwitchViewHolder

class MultipleViewTypesResolverActivity : BaseSampleActivity() {

    class ItemSelectedViewModel : ViewEventViewModel<ViewEvent, OnMultiItemSelectListener>(
        OnMultiItemSelectListener(
            viewHolderType = SimpleSelectableItemViewHolder::class
        )
    )

    class ItemCheckedViewModel : ViewEventViewModel<ViewEvent, OnMultiItemCheckListener>(
        OnMultiItemCheckListener(
            viewHolderType = SimpleSelectableCheckBoxViewHolder::class,
            viewId = R.id.checkBox
        )
    )

    class ItemSwitchedViewModel : ViewEventViewModel<ViewEvent, OnMultiItemCheckListener>(
        OnMultiItemCheckListener(
            viewHolderType = SimpleSelectableSwitchViewHolder::class,
            viewId = R.id.switchButton
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Types Resolver"

        val itemSelectedViewModel: ItemSelectedViewModel by viewModels()
        val checkBoxItemSelectedViewModel: ItemCheckedViewModel by viewModels()
        val switchItemSelectedViewModel: ItemSwitchedViewModel by viewModels()

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
            .items(items)
            .map(CopyrightModel::class, CopyrightViewHolder::class)
            .add(itemSelectedViewModel.observe(this) {
                showToast("onItemSelected ${it.position}")
            })
            .add(OnClickEventListener(SimpleSelectableItemViewHolder::class) {
                showToast("onItemClick ${it.position}")
            })
            .add(checkBoxItemSelectedViewModel.observe(this) {
                showToast(
                    String.format(
                        "Checkbox click %d\n" + "%d selected items",
                        it.position,
                        checkBoxItemSelectedViewModel.viewEventListener.selectedItemsCount
                    )
                )
            })
            .add(switchItemSelectedViewModel.observe(this) {
                showToast(
                    String.format(
                        "Switch click %d\n" + "%d selected items",
                        it.position,
                        switchItemSelectedViewModel.viewEventListener.selectedItemsCount
                    )
                )
            })
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
