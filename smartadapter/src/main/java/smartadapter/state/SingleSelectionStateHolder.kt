package smartadapter.state

import smartadapter.Position

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

/**
 * Extends [SelectionStateHolder] and contains the logic for the single selection states for recycler adapter positions.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
class SingleSelectionStateHolder : SelectionStateHolder() {

    /**
     * Adds the position to the data set and [.disable]s any old positions.
     * @param position the adapter position
     */
    override fun enable(position: Position) {
        for (oldPositions in selectedItems) {
            disable(oldPositions)
        }
        clear()
        super.enable(position)
    }

    /**
     * Removes the position from the data set and calls [smartadapter.SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun disable(position: Position) {
        super.disable(position)
        smartRecyclerAdapter.smartNotifyItemChanged(position)
    }
}
