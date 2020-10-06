package smartadapter.diffutil.extension

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension
import smartadapter.diffutil.SimpleDiffUtilExtension

/**
 * Helper method to resolve target [DiffUtilExtension]
 */
fun SmartRecyclerAdapter.getDiffUtil(
    identifier: Any = SimpleDiffUtilExtension::class
) = smartRecyclerAdapterExtensions[identifier] as DiffUtilExtension

/**
 * Helper method to resolve target [DiffUtilExtension] and call [DiffUtilExtension.diffSwapList]
 */
fun SmartRecyclerAdapter.diffSwapList(
    newList: List<*>,
    identifier: Any = SimpleDiffUtilExtension::class
) = getDiffUtil(identifier).diffSwapList(newList)