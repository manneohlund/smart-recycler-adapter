package smartadapter.state

import smartadapter.Position

/*
 * Created by Manne Öhlund on 2019-08-09.
 * Copyright (c) All rights reserved.
 */

/**
 * Defines the SortedSet for enabled adapter positions.
 */
interface SmartStateHolder {

    /**
     * Adds the position to the data set.
     * @param position the adapter position
     */
    fun enable(position: Position)

    /**
     * Removes the position from the data set.
     * @param position the adapter position
     */
    fun disable(position: Position)

    /**
     * Enables or disables the position in the data set.
     * @param position the adapter position
     */
    fun toggle(position: Position)

    /**
     * Clears all the stored states.
     */
    fun clear()
}
