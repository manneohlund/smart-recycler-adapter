package smartadapter.state;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

/**
 * Extends {@link SelectionStateHolder} and contains the logic for the single selection states for recycler adapter positions.
 */
public class SingleSelectionStateHolder extends SelectionStateHolder {

    /**
     * Adds the position to the data set and {@link #disable(int)}s any old positions.
     * @param position the adapter position
     */
    @Override
    public void enable(int position) {
        for (int oldPositions : enabledAdapterPositions) {
            disable(oldPositions);
        }
        clear();
        super.enable(position);
    }

    /**
     * Removes the position from the data set and calls {@link smartadapter.SmartRecyclerAdapter#smartNotifyItemChanged(int)}.
     * @param position the adapter position
     */
    @Override
    public void disable(int position) {
        super.disable(position);
        smartRecyclerAdapter.smartNotifyItemChanged(position);
    }
}
