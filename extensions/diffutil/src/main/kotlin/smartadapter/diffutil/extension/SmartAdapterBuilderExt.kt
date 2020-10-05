package smartadapter.diffutil.extension

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension

/**
 * Helper method to add [DiffUtilExtension] to [SmartRecyclerAdapter].
 */
fun SmartAdapterBuilder.add(diffUtilExtension: DiffUtilExtension): SmartAdapterBuilder {
    addExtension(diffUtilExtension)
    return this
}