package smartadapter.state;

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import java.util.TreeSet;

import smartadapter.SmartRecyclerAdapter;

/**
 * Implementation of {@link SmartStateHolder} and contains the logic of the selection states for recycler adapter positions.
 */
public class SelectionStateHolder implements SmartStateHolder {

    protected SmartRecyclerAdapter smartRecyclerAdapter;
    protected TreeSet<Integer> enabledAdapterPositions = new TreeSet<>();

    @Override
    public void enable(int position) {
        enabledAdapterPositions.add(position);
    }

    @Override
    public void disable(int position) {
        enabledAdapterPositions.remove(position);
    }

    /**
     * Toggles selection of a position in adapter and calls {@link SmartRecyclerAdapter#smartNotifyItemChanged(int)}.
     * @param position the adapter position
     */
    @Override
    public void toggle(int position) {
        if (enabledAdapterPositions.contains(position))
            disable(position);
        else
            enable(position);

        smartRecyclerAdapter.smartNotifyItemChanged(position);
    }

    @Override
    public void clear() {
        enabledAdapterPositions.clear();
    }

    /**
     * Checks if position is selected.
     * @param position position in adapter
     * @return true if position is contained in the selection set
     */
    public boolean isSelected(int position) {
        return enabledAdapterPositions.contains(position);
    }

    /**
     * Provides sorted set of selected positions.
     * @return TreeSet of selected positions
     */
    public TreeSet<Integer> getSelectedItems() {
        return enabledAdapterPositions;
    }

    /**
     * Provides selected item count.
     * @return selected item count
     */
    public int getSelectedItemsCount() {
        return enabledAdapterPositions.size();
    }

    /**
     * Sets the target {@link SmartRecyclerAdapter}.
     * @param smartRecyclerAdapter the target smart adapter
     */
    public void setSmartRecyclerAdapter(SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    /**
     * Removes selected items in the adapter with animation then clears the state holder set.
     */
    public void removeSelections() {
        for (int position : enabledAdapterPositions.descendingSet()) {
            smartRecyclerAdapter.removeItem(position);
        }
        enabledAdapterPositions.clear();
    }
}