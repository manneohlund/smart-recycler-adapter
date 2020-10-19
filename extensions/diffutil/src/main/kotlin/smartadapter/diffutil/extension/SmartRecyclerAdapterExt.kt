package smartadapter.diffutil.extension

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import androidx.lifecycle.LifecycleCoroutineScope
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.DiffUtilExtension
import smartadapter.diffutil.SimpleDiffUtilExtension

/**
 * Helper method to resolve target [DiffUtilExtension]
 */
fun SmartRecyclerAdapter.getDiffUtil(
    identifier: Any = SimpleDiffUtilExtension::class
) = smartExtensions[identifier] as DiffUtilExtension

/**
 * Helper method to resolve target [DiffUtilExtension] and call [DiffUtilExtension.diffSwapList]
 */
fun SmartRecyclerAdapter.diffSwapList(
    newList: List<*>,
    identifier: Any = SimpleDiffUtilExtension::class
) = getDiffUtil(identifier).diffSwapList(newList)

/**
 * Helper method to resolve target [DiffUtilExtension] and call [DiffUtilExtension.diffSwapList]
 */
fun SmartRecyclerAdapter.diffSwapList(
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    newList: List<*>,
    identifier: Any = SimpleDiffUtilExtension::class,
    callback: (Result<Boolean>) -> Unit = {}
) = getDiffUtil(identifier).diffSwapList(lifecycleCoroutineScope, newList, callback)