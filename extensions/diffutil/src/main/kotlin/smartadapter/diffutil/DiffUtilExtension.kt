package smartadapter.diffutil

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.DiffUtil
import smartadapter.SmartRecyclerAdapter
import smartadapter.binders.SmartRecyclerAdapterExtension

/**
 * Defines basic functionality of the DiffUtilExtension.
 */
abstract class DiffUtilExtension : DiffUtil.Callback(),
    SmartRecyclerAdapterExtension {

    abstract var smartRecyclerAdapter: SmartRecyclerAdapter

    /**
     * Sets the predicate that will be called to check if items are the same and content are the same.
     * @see DiffPredicate
     *
     * @param diffPredicate predicate
     */
    abstract fun setDiffPredicate(diffPredicate: DiffPredicate<*>)

    /**
     * Swaps the [smartadapter.SmartRecyclerAdapter] item list with new item list and animates the swap.
     * @param newList new item list
     */
    abstract fun diffSwapList(newList: List<*>)

    /**
     * Old vs new item compare, can be typed if the [smartadapter.SmartRecyclerAdapter] only contains one type of items.
     * @param <I> the type of the target data
     */
    interface DiffPredicate<Any> {

        /**
         * Compares two items.
         * @see [DiffUtil.Callback.areItemsTheSame].]
         */
        fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean

        /**
         * Compares content of two items.
         * @see [DiffUtil.Callback.areContentsTheSame].]
         */
        fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean
    }
}
