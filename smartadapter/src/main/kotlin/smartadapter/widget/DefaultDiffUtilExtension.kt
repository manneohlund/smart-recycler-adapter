package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.DiffUtil
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

@Suppress("UNCHECKED_CAST")
class DefaultDiffUtilExtension : DiffUtilExtension() {

    override var smartRecyclerAdapter: SmartRecyclerAdapter? = null
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

    override fun diffSwapList(newList: MutableList<*>) {
        smartRecyclerAdapter?.let { smartRecyclerAdapter ->
            this.oldList = smartRecyclerAdapter.getItems()
            this.newList = newList as MutableList<Any>
            val diffResult = DiffUtil.calculateDiff(this)
            diffResult.dispatchUpdatesTo(smartRecyclerAdapter)
            smartRecyclerAdapter.setItems(newList, false)
            smartRecyclerAdapter.updateItemCount()
        } ?: throw RuntimeException("SmartRecyclerAdapter is not set")
    }
}
