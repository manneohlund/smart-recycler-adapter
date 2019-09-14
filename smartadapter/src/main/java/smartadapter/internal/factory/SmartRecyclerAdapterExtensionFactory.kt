package smartadapter.internal.factory

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartExtensionBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.widget.BasicDragAndDropExtension
import smartadapter.widget.BasicSwipeExtension
import java.util.*

/**
 * Builds [SmartExtensionBuilder] extensions.
 *
 * @see BasicSwipeExtension
 *
 * @see BasicDragAndDropExtension
 */
class SmartRecyclerAdapterExtensionFactory {

    private val smartExtensionBuilders = ArrayList<SmartExtensionBuilder<*>>()

    fun add(smartExtensionBuilder: SmartExtensionBuilder<*>) {
        smartExtensionBuilders.add(smartExtensionBuilder)
    }

    fun build(smartRecyclerAdapter: SmartRecyclerAdapter, recyclerView: RecyclerView) {
        for (smartExtension in smartExtensionBuilders) {
            smartExtension.apply {
                this.smartRecyclerAdapter = smartRecyclerAdapter
                this.recyclerView = recyclerView
            }.build()
        }
    }
}
