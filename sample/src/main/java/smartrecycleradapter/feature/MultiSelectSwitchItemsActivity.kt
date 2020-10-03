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
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewmodel.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.SimpleSelectableSwitchViewHolder

class MultiSelectSwitchItemsActivity : BaseSampleActivity() {

    class MultiItemCheckViewModel : ViewEventViewModel<ViewEvent, OnMultiItemCheckListener>(
        OnMultiItemCheckListener(
            viewHolderType = SimpleSelectableSwitchViewHolder::class,
            viewId = R.id.switchButton
        )
    )

    private val multiItemCheckViewModel: MultiItemCheckViewModel by viewModels()

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi Switch Select"

        val items = (0..100).toMutableList()

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableSwitchViewHolder::class)
            .add(multiItemCheckViewModel.observe(this) {
                handleCheckEvent(it)
            })
            .add(OnClickEventListener {
                showToast("onClick ${it.position}")
            })
            .into(recyclerView)
    }

    private fun handleCheckEvent(it: ViewEvent) {
        showToast("Item click ${it.position}\n" +
                "${multiItemCheckViewModel.viewEventListener.selectedItemsCount} of " +
                "${it.adapter.itemCount} selected items")

        supportActionBar?.subtitle =
            "${multiItemCheckViewModel.viewEventListener.selectedItemsCount} / ${it.adapter.itemCount} selected"
    }
}
