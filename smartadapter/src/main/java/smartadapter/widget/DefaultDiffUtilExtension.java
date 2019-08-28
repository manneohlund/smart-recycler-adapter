package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import smartadapter.SmartRecyclerAdapter;

@SuppressWarnings("unchecked")
public class DefaultDiffUtilExtension extends DiffUtilExtension {

    private SmartRecyclerAdapter smartRecyclerAdapter;
    private DiffPredicate diffPredicate;
    private List oldList;
    private List newList;

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return diffPredicate.areItemsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return diffPredicate.areContentsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    @Override
    public void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    @Override
    public void setDiffPredicate(@NonNull DiffPredicate diffPredicate) {
        this.diffPredicate = diffPredicate;
    }

    @Override
    public void diffSwapList(@NonNull List newList) {
        this.oldList = smartRecyclerAdapter.getItems();
        this.newList = newList;
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(this);
        diffResult.dispatchUpdatesTo(smartRecyclerAdapter);
        smartRecyclerAdapter.setItems(newList, false);
    }
}
