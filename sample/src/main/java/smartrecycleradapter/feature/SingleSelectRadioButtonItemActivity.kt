package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewId
import smartadapter.listener.OnClick
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SelectionStateHolder
import smartadapter.state.SingleSelectionStateHolder
import smartrecycleradapter.R

class SingleSelectRadioButtonItemActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    lateinit var onSingleRadioButtonItemSelectedListener: OnSingleRadioButtonItemSelectedListener
    var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single RadioButton Select"

        val items = (0..100).toMutableList()

        onSingleRadioButtonItemSelectedListener = OnSingleRadioButtonItemSelectedListener { view, smartRecyclerAdapter, position ->
                Toast.makeText(
                    applicationContext,
                    String.format(
                        "Item click %d\n" +
                                "%d of %d selected items",
                        position,
                        singleRadioButtonStateHolder.selectedItemsCount,
                        this.smartRecyclerAdapter.itemCount
                    ),
                    Toast.LENGTH_LONG
                ).show()

                deleteMenuItem?.isVisible =
                    onSingleRadioButtonItemSelectedListener.selectionStateHolder.selectedItemsCount > 0
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleSelectableRadioButtonViewHolder::class)
            .addViewEventListener(onSingleRadioButtonItemSelectedListener)
            .addViewEventListener(object : OnItemClickListener {
                override val listener: OnClick = { view, adapter, position ->
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
                onSingleRadioButtonItemSelectedListener.selectionStateHolder.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

var singleRadioButtonStateHolder = SingleSelectionStateHolder()

class OnSingleRadioButtonItemSelectedListener(
    override val selectionStateHolder: SelectionStateHolder  = singleRadioButtonStateHolder,
    override val viewId: ViewId = R.id.radioButton,
    override val listener: OnClick
) : OnItemSelectedListener
