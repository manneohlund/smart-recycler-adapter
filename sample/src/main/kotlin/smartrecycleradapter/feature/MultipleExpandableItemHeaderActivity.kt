package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2020-10-10.
 * Copyright (c) All rights reserved.
 */

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.stickyheader.StickyHeaderItemDecorationExtension
import smartadapter.viewevent.listener.OnMultiItemSelectListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewmodel.ViewEventViewModel
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleHeaderViewHolder
import smartrecycleradapter.utils.showToast

class MultipleExpandableItemHeaderActivity : BaseSampleActivity() {

    class MultiItemSelectViewModel : ViewEventViewModel<ViewEvent, OnMultiItemSelectListener>(
        OnMultiItemSelectListener(
            enableOnLongClick = false,
            viewHolderType = SimpleExpandableItemViewHolder::class,
            selectableItemType = Integer::class,
            viewId = R.id.itemTitle
        )
    )

    private val multiItemSelectViewModel: MultiItemSelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi Expandable Item +"

        val items = (0..10000).toMutableList().toIntArray().mapIndexed { index, item ->
            when {
                index % 5 == 0 -> arrayOf(item.toString(), item)
                else -> arrayOf(item)
            }
        }.toTypedArray().flatten()

        SmartRecyclerAdapter
            .items(items)
            .map(String::class, SimpleHeaderViewHolder::class)
            .map(Integer::class, SimpleExpandableItemViewHolder::class)
            .add(multiItemSelectViewModel.observe(this) {
                supportActionBar?.subtitle =
                    "${multiItemSelectViewModel.viewEventListener.selectedItemsCount} / ${items.size} expanded"
            })
            .add(StickyHeaderItemDecorationExtension(
                headerItemType = String::class
            ) { motionEvent, itemPosition ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    showToast("Header $itemPosition clicked")
                }
            })
            .into<SmartRecyclerAdapter>(recyclerView)

        SimpleHeaderViewHolder.color = Color.DKGRAY
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        with(menuInflater) {
            inflate(R.menu.expand, menu)
            inflate(R.menu.collapse, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.expand_all -> multiItemSelectViewModel.viewEventListener.enableAll()
            R.id.collapse_all -> multiItemSelectViewModel.viewEventListener.disableAll()
        }
        return super.onOptionsItemSelected(item)
    }
}
