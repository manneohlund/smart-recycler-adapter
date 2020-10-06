package smartrecycleradapter.feature

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension
import smartadapter.diffutil.SimpleDiffUtilExtension
import smartadapter.diffutil.extension.add
import smartadapter.diffutil.extension.diffSwapList
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import kotlin.random.Random.Default.nextInt

/*
 * Created by Manne Öhlund on 2019-08-23.
 * Copyright (c) All rights reserved.
 */

class DiffUtilActivity : BaseSampleActivity() {

    val maxItemCount = nextInt(20, 100)
    val items = (0..maxItemCount).toMutableList()

    private lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

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

        supportActionBar?.title = "Diff Util Sample"

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleItemViewHolder::class)
            .add(SimpleDiffUtilExtension(predicate))
            .into(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.scroll_top, menu)
        menuInflater.inflate(R.menu.sort, menu)
        menuInflater.inflate(R.menu.shuffle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.scroll_top -> {
                recyclerView.smoothScrollToPosition(0)
            }
            R.id.sort -> {
                smartRecyclerAdapter.diffSwapList(items)
                recyclerView.scrollToPosition(0)
            }
            R.id.shuffle -> {
                smartRecyclerAdapter.diffSwapList(
                    items.shuffled()
                        .filter {
                            it % nextInt(1, maxItemCount/5) == 1
                        }
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
