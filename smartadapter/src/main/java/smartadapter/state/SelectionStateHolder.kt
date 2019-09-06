package smartadapter.state

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import smartadapter.SmartRecyclerAdapter
import java.util.*

/**
 * Implementation of [SmartStateHolder] and contains the logic of the selection states for recycler adapter positions.
 */
open class SelectionStateHolder : SmartStateHolder {

    /**
     * Provides sorted set of selected positions.
     * @return TreeSet of selected positions
     */
    var selectedItems = TreeSet<Int>()
        protected set

    /**
     * Provides selected item count.
     * @return selected item count
     */
    val selectedItemsCount: Int
        get() = selectedItems.size

    override fun enable(position: Int) {
        selectedItems.add(position)
    }

    override fun disable(position: Int) {
        selectedItems.remove(position)
    }

    /**
     * Toggles selection of a position in adapter and calls [SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun toggle(position: Int) {
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
    fun isSelected(position: Int): Boolean {
        return selectedItems.contains(position)
    }

    /**
     * Sets the target [SmartRecyclerAdapter].
     * @param smartRecyclerAdapter the target smart adapter
     */
    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

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