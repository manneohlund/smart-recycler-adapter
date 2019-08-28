package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import smartadapter.viewholder.SmartAdapterHolder;

/**
 * Defines basic functionality of the DiffUtilExtension.
 */
public abstract class DiffUtilExtension extends DiffUtil.Callback implements SmartAdapterHolder {

    /**
     * Sets the predicate that will be called to check if items are the same and content are the same.
     * @see DiffPredicate
     * @param diffPredicate predicate
     */
    public abstract void setDiffPredicate(@NonNull DiffPredicate diffPredicate);

    /**
     * Swaps the {@link smartadapter.SmartRecyclerAdapter} item list with new item list and animates the swap.
     * @param newList new item list
     */
    public abstract void diffSwapList(@NonNull List newList);

    /**
     * Old vs new item compare, can be typed if the {@link smartadapter.SmartRecyclerAdapter} only contains one type of items.
     * @param <I> the type of the target data
     */
    public interface DiffPredicate<I> {

        boolean areItemsTheSame(@NonNull I oldItem, @NonNull I newItem);

        boolean areContentsTheSame(@NonNull I oldItem, @NonNull I newItem);
    }
}
