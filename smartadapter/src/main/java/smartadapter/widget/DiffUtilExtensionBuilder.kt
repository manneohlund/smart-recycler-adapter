package smartadapter.widget

/*
 * Created by Manne Öhlund on 2019-08-21.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

import smartadapter.SmartExtensionBuilder
import smartadapter.SmartRecyclerAdapter

class DiffUtilExtensionBuilder : SmartExtensionBuilder<DiffUtilExtension> {
    var diffUtilExtension: DiffUtilExtension
    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    override lateinit var recyclerView: RecyclerView
    var diffPredicate: DiffUtilExtension.DiffPredicate<*>? = null

    constructor() {
        this.diffUtilExtension = DefaultDiffUtilExtension()
    }

    constructor(diffUtilExtension: DiffUtilExtension) {
        this.diffUtilExtension = diffUtilExtension
    }

    override fun build(): DiffUtilExtension {
        diffUtilExtension.smartRecyclerAdapter = smartRecyclerAdapter
        diffUtilExtension.setDiffPredicate(diffPredicate!!)
        return diffUtilExtension
    }
}
