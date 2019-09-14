package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewEventId
import smartadapter.ViewId
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartrecycleradapter.R

class MultiSelectCheckBoxItemsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    lateinit var onCheckBoxItemSelectedListener: OnCheckBoxItemSelectedListener
    var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multi CheckBox Select"

        val items = (0..100).toMutableList()

        onCheckBoxItemSelectedListener = object : OnCheckBoxItemSelectedListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Item click %d\n" +
                            "%d of %d selected items",
                        position,
                        selectionStateHolder.selectedItemsCount,
                        smartRecyclerAdapter.itemCount
                    ),
                    Toast.LENGTH_LONG
                ).show()
                supportActionBar?.subtitle =
                    "${checkBoxStateHolder.selectedItemsCount} / ${items.size} selected"

                deleteMenuItem?.isVisible =
                    onCheckBoxItemSelectedListener.selectionStateHolder.selectedItemsCount > 0
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableCheckBoxViewHolder::class)
            .addViewEventListener(onCheckBoxItemSelectedListener)
            .addViewEventListener(object : OnItemClickListener {
                override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                    Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT)
                        .show()
                }
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
                onCheckBoxItemSelectedListener.selectionStateHolder.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

var checkBoxStateHolder = SelectionStateHolder()

interface OnCheckBoxItemSelectedListener : OnItemSelectedListener {

    override val selectionStateHolder: SelectionStateHolder
        get() = checkBoxStateHolder

    override val viewId: ViewId
        get() = R.id.checkBox
}
