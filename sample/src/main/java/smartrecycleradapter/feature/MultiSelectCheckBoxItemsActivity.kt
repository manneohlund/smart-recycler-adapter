package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.extension.add
import smartadapter.viewevent.listeners.OnClickEventListener
import smartadapter.viewevent.listeners.OnMultiItemCheckListener
import smartadapter.viewevent.models.ViewEvent
import smartadapter.viewevent.viewmodels.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.utils.showToast
import smartrecycleradapter.viewholder.SimpleSelectableCheckBoxViewHolder

class MultiSelectCheckBoxItemsActivity : BaseSampleActivity() {

    class MultiItemCheckViewModel : ViewEventViewModel<ViewEvent, OnMultiItemCheckListener>(
        OnMultiItemCheckListener(
            viewHolderType = SimpleSelectableCheckBoxViewHolder::class,
            viewId = R.id.checkBox
        )
    )

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private val multiItemCheckViewModel: MultiItemCheckViewModel by viewModels()
    private var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi CheckBox Select"

        val items = (0..100).toMutableList()

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableCheckBoxViewHolder::class)
            .add(multiItemCheckViewModel.observe(this) {
                handleCheckEvent(it)
            })
            .add(OnClickEventListener {
                showToast("onClick ${it.position}")
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
                multiItemCheckViewModel.viewEventListener.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleCheckEvent(it: ViewEvent) {
        showToast("Item click ${it.position}\n" +
                "${multiItemCheckViewModel.viewEventListener.selectedItemsCount} of " +
                "${it.adapter.itemCount} selected items")

        supportActionBar?.subtitle =
            "${multiItemCheckViewModel.viewEventListener.selectedItemsCount} / ${it.adapter.itemCount} selected"

        deleteMenuItem?.isVisible = multiItemCheckViewModel.viewEventListener.selectedItemsCount > 0
    }
}
