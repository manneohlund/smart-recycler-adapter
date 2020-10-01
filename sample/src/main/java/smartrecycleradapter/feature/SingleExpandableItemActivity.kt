package smartrecycleradapter.feature

import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listeners.OnSingleItemSelectListener
import smartadapter.viewevent.models.ViewEvent
import smartadapter.viewevent.viewmodels.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.utils.showToast


/*
 * Created by Manne Öhlund on 2019-08-23.
 * Copyright (c) All rights reserved.
 */

class SingleExpandableItemActivity : BaseSampleActivity() {

    class SingleItemSelectViewModel : ViewEventViewModel<ViewEvent, OnSingleItemSelectListener>(
        OnSingleItemSelectListener(
            viewHolderType = SimpleExpandableItemViewHolder::class,
            viewId = R.id.itemTitle
        )
    )

    private val singleItemSelectViewModel: SingleItemSelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single Expandable Item"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleExpandableItemViewHolder::class)
            .add(singleItemSelectViewModel.observe(this) {
                showToast("onSelect ${it.position}")
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }
}
