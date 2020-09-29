package smartadapter.state

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import java.util.TreeSet

/**
 * Implementation of [SmartStateHolder] and contains the logic of the selection states for recycler adapter positions.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
open class SelectionStateHolder : SmartStateHolder {

    /**
     * The target [SmartRecyclerAdapter].
     */
    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    /**
     * Provides sorted set of selected positions.
     */
    var selectedItems = TreeSet<Int>()
        protected set

    /**
     * Provides selected item count.
     */
    val selectedItemsCount: Int
        get() = selectedItems.size

    override fun enable(position: Position) {
        selectedItems.add(position)
    }

    override fun disable(position: Position) {
        selectedItems.remove(position)
    }

    /**
     * Toggles selection of a position in adapter and calls [SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun toggle(position: Position) {
        if (selectedItems.contains(position))
            disable(position)
        else
            enable(position)

        smartRecyclerAdapter.smartNotifyItemChanged(position)
    }

    override fun clear() {
        selectedItems.clear()
    }

    /**
     * Checks if position is selected.
     * @param position position in adapter
     * @return true if position is contained in the selection set
     */
    fun isSelected(position: Position): Boolean {
        return selectedItems.contains(position)
    }

    /**
     * Removes selected items in the adapter with animation then clears the state holder set.
     */
    fun removeSelections() {
        for (position in selectedItems.descendingSet()) {
            smartRecyclerAdapter.removeItem(position)
        }
        selectedItems.clear()
    }
}
