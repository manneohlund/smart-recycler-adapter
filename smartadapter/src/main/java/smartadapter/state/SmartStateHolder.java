package smartadapter.state;

/*
 * Created by Manne Öhlund on 2019-08-09.
 * Copyright (c) All rights reserved.
 */

import java.util.TreeSet;

/**
 * Defines the SortedSet for enabled adapter positions.
 */
public interface SmartStateHolder {

    TreeSet<Integer> enabledAdapterPositions = new TreeSet<>();

    /**
     * Adds the position to the data set.
     * @param position the adapter position
     */
    default void enable(int position) {
        enabledAdapterPositions.add(position);
    }

    /**
     * Removes the position from the data set.
     * @param position the adapter position
     */
    default void disable(int position) {
        enabledAdapterPositions.remove(position);
    }

    /**
     * Enables or disables the position in the data set.
     * @param position the adapter position
     */
    default void toggle(int position) {
        if (enabledAdapterPositions.contains(position))
            disable(position);
        else
            enable(position);
    }
}
