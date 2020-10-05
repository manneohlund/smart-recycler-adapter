package smartadapter.diffutil

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.DiffUtil
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Default implementation of [DiffUtilExtension]
 */
@Suppress("UNCHECKED_CAST")
class SimpleDiffUtilExtension(
    private var predicate: DiffPredicate<*>? = null,
    override val identifier: Any = SimpleDiffUtilExtension::class
) : DiffUtilExtension() {

    init {
        predicate?.let {
            diffPredicate = predicate as DiffPredicate<Any>
        }
    }

    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private lateinit var diffPredicate: DiffPredicate<Any>
    private lateinit var oldList: List<Any>
    private lateinit var newList: List<Any>

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Position, newItemPosition: Position): Boolean {
        return diffPredicate.areItemsTheSame(
            oldList[oldItemPosition],
            newList[newItemPosition]
        )
    }

    override fun areContentsTheSame(oldItemPosition: Position, newItemPosition: Position): Boolean {
        return diffPredicate.areContentsTheSame(
            oldList[oldItemPosition],
            newList[newItemPosition]
        )
    }

    override fun setDiffPredicate(diffPredicate: DiffPredicate<*>) {
        this.diffPredicate = diffPredicate as DiffPredicate<Any>
    }

    override fun diffSwapList(newList: List<*>) {
        smartRecyclerAdapter.let { smartRecyclerAdapter ->
            this.oldList = smartRecyclerAdapter.getItems()
            this.newList = newList as MutableList<Any>
            val diffResult = DiffUtil.calculateDiff(this)
            diffResult.dispatchUpdatesTo(smartRecyclerAdapter)
            smartRecyclerAdapter.setItems(newList, false)
            smartRecyclerAdapter.updateItemCount()
        }
    }

    override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter
    }
}
