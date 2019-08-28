package smartadapter.state;

/*
 * Created by Manne Öhlund on 2019-08-09.
 * Copyright (c) All rights reserved.
 */

/**
 * Defines the SortedSet for enabled adapter positions.
 */
public interface SmartStateHolder {

    /**
     * Adds the position to the data set.
     * @param position the adapter position
     */
    void enable(int position);

    /**
     * Removes the position from the data set.
     * @param position the adapter position
     */
    void disable(int position);

    /**
     * Enables or disables the position in the data set.
     * @param position the adapter position
     */
    void toggle(int position);

    /**
     * Clears all the stored states.
     */
    void clear();
}
