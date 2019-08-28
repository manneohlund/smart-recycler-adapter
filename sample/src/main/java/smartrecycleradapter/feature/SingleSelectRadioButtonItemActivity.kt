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
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.state.SingleSelectionStateHolder
import smartrecycleradapter.R

class SingleSelectRadioButtonItemActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;
    lateinit var onSingleRadioButtonItemSelectedListener: OnSingleRadioButtonItemSelectedListener
    var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Single RadioButton Select"

        val items = (0..100).toList()

        onSingleRadioButtonItemSelectedListener = object : OnSingleRadioButtonItemSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Item click %d\n" +
                                "%d of %d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount,
                                smartRecyclerAdapter.itemCount),
                        Toast.LENGTH_LONG).show()

                deleteMenuItem?.isVisible = onSingleRadioButtonItemSelectedListener.selectionStateHolder.selectedItemsCount > 0
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleSelectableRadioButtonViewHolder::class.java)
                .addViewEventListener(onSingleRadioButtonItemSelectedListener)
                .addViewEventListener(OnItemClickListener {
                    view, actionId, position ->
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                })
                .into(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete, menu)
        deleteMenuItem = menu?.findItem(R.id.delete)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.delete -> {
                onSingleRadioButtonItemSelectedListener.selectionStateHolder.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

var singleRadioButtonStateHolder = SingleSelectionStateHolder()

interface OnSingleRadioButtonItemSelectedListener : OnItemSelectedListener {

    @JvmDefault
    override fun getSelectionStateHolder() = singleRadioButtonStateHolder

    @JvmDefault
    override fun getViewId() = R.id.radioButton
}