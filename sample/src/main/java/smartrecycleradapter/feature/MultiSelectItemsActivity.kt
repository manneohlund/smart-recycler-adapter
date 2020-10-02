package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listener.OnMultiItemSelectListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewmodel.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.SimpleSelectableItemViewHolder

class MultiSelectItemsActivity : BaseSampleActivity() {

    class MultiItemSelectViewModel : ViewEventViewModel<ViewEvent, OnMultiItemSelectListener>(
        OnMultiItemSelectListener(
            enableOnLongClick = true
        )
    )

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private val multiItemSelectViewModel by lazy { MultiItemSelectViewModel() }
    private var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Items Select"

        val items = (0..100).toMutableList()

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableItemViewHolder::class)
            .add(multiItemSelectViewModel.observe(this) {
                handleCheckEvent(it)
            })
            .into(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete, menu)
        deleteMenuItem = menu?.findItem(R.id.delete)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.delete -> {
                multiItemSelectViewModel.viewEventListener.removeSelections()
                supportActionBar?.subtitle =
                    "${multiItemSelectViewModel.viewEventListener.selectedItemsCount} / ${smartRecyclerAdapter.itemCount} selected"
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleCheckEvent(it: ViewEvent) {
        when (it) {
            is ViewEvent.OnClick -> {
                showToast("Item click ${it.position}")
            }
            is ViewEvent.OnItemSelected -> {
                val selected = if (it.isSelected) "selected" else "unselected"
                showToast("""
                            Item $selected ${it.position}
                            ${multiItemSelectViewModel.viewEventListener.selectedItemsCount} of ${it.adapter.itemCount} selected items
                            """.trimIndent())
            }
        }

        supportActionBar?.subtitle =
            "${multiItemSelectViewModel.viewEventListener.selectedItemsCount} / ${it.adapter.itemCount} selected"

        deleteMenuItem?.isVisible = multiItemSelectViewModel.viewEventListener.selectedItemsCount > 0

        smartRecyclerAdapter.smartNotifyItemChanged(it.position)
    }
}
