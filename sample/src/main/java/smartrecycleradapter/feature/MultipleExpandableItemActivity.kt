package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2020-09-23.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listeners.OnMultiItemSelectListener
import smartadapter.viewevent.models.ViewEvent
import smartadapter.viewevent.viewmodels.ViewEventViewModel
import smartadapter.viewholder.OnItemSelectedEventListener
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R

class MultipleExpandableItemActivity : BaseSampleActivity() {

    class MultiItemSelectViewModel : ViewEventViewModel<ViewEvent, OnMultiItemSelectListener>(
        OnMultiItemSelectListener(
            enableOnLongClick = false,
            viewHolderType = SimpleExpandableItemViewHolder::class,
            viewId = R.id.itemTitle
        )
    )

    private val multiItemSelectViewModel: MultiItemSelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi Expandable Item"

        val items = (0..100).toMutableList()

        SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleExpandableItemViewHolder::class)
            .add(multiItemSelectViewModel.observe(this) {
                supportActionBar?.subtitle =
                    "${multiItemSelectViewModel.viewEventListener.selectedItemsCount} / ${items.size} expanded"
            })
            .into<SmartRecyclerAdapter>(recyclerView)
    }
}

class SimpleExpandableItemViewHolder(parentView: ViewGroup) : SmartViewHolder<Int>(
    LayoutInflater.from(parentView.context)
        .inflate(R.layout.simple_expandable_item, parentView, false)
), OnItemSelectedEventListener {

    private val title: TextView = itemView.findViewById(R.id.itemTitle)
    private val subItem: LinearLayout = itemView.findViewById(R.id.subItemContainer)
    private val subItemTitle: TextView = itemView.findViewById(R.id.subItemTitle)

    override fun bind(item: Int) {
        title.text = "Item $item"
        subItemTitle.text = "Sub item of '$item'"
    }

    override fun onItemSelect(event: ViewEvent.OnItemSelected) {
        when (event.isSelected) {
            true -> {
                title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0)
                subItem.visibility = View.VISIBLE
            }
            false -> {
                title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0)
                subItem.visibility = View.GONE
            }
        }
    }
}
