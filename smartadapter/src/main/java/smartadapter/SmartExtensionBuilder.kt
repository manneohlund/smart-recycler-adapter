package smartadapter

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

/**
 * Basic requirements for an ExtensionBuilder.
 * @param <T> extension type returned by B [SmartExtensionBuilder]
 */
interface SmartExtensionBuilder<T> {

    /**
     * Reference holder to SmartRecyclerAdapter.
     * @see [SmartRecyclerAdapter]]
     */
    var smartRecyclerAdapter: SmartRecyclerAdapter

    /**
     * Reference holder to RecyclerView.
     * @see [RecyclerView]]
     */
    var recyclerView: RecyclerView

    /**
     * Builds the SmartExtension.
     * @return SmartExtension
     */
    fun build(): T
}
