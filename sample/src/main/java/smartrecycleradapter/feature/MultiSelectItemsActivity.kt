package smartrecycleradapter.feature

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnItemLongClickSelectedListener
import smartrecycleradapter.R

fun View.setBackgroundAttribute(attribute: Int) {
    setBackgroundDrawable(with(TypedValue()) {
        context.theme.resolveAttribute(attribute, this, true)
        ContextCompat.getDrawable(context, resourceId)
    })
}

class MultiSelectItemsActivity : BaseSampleActivity() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter;
    lateinit var onItemSelectedListener: OnItemLongClickSelectedListener
    var deleteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Multiple Items Select"

        val items = (0..100).toList()

        onItemSelectedListener = object : OnItemLongClickSelectedListener {
            override fun onViewEvent(view: View, actionId: Int, position: Int) {
                Toast.makeText(applicationContext,
                        String.format("Item click %d\n" +
                                "%d of %d selected items",
                                position,
                                selectionStateHolder.selectedItemsCount,
                                smartRecyclerAdapter.itemCount),
                        Toast.LENGTH_LONG).show()

                deleteMenuItem?.isVisible = onItemSelectedListener.selectionStateHolder.selectedItemsCount > 0

                smartRecyclerAdapter.smartNotifyItemChanged(position)
            }
        }

        smartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleSelectableItemViewHolder::class.java)
                .addViewEventListener(onItemSelectedListener)
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
                onItemSelectedListener.selectionStateHolder.removeSelections()
                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}