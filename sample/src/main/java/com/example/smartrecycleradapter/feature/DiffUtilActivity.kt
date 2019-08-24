package com.example.smartrecycleradapter.feature

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.widget.DiffUtilExtension
import smartadapter.widget.DiffUtilExtensionBuilder

/*
 * Created by Manne Öhlund on 2019-08-23.
 * Copyright (c) All rights reserved.
 */

class DiffUtilActivity : BaseSampleActivity() {

    val items = (0..15).toList()
    lateinit var diffUtilExtension: DiffUtilExtension

    private val predicate = object : DiffUtilExtension.DiffPredicate<Int> {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Diff Util Sample";

        val smartAdapter: SmartRecyclerAdapter = SmartRecyclerAdapter
                .items(items)
                .map(Integer::class.java, SimpleItemViewHolder::class.java)
                .into(recyclerView)

        diffUtilExtension = DiffUtilExtensionBuilder()
                .setSmartRecyclerAdapter(smartAdapter)
                .setDiffPredicate(predicate)
                .build()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shuffle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.shuffle -> {
                diffUtilExtension.diffSwapList(items.shuffled())
                recyclerView.scrollToPosition(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
