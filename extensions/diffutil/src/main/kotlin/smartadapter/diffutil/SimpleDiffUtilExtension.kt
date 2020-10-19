package smartadapter.diffutil

/*
 * Created by Manne Öhlund on 2020-10-05.
 * Copyright (c) All rights reserved.
 */

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Default implementation of [DiffUtilExtension]
 */
@Suppress("UNCHECKED_CAST")
class SimpleDiffUtilExtension(
    private var predicate: DiffPredicate<*>? = null,
    override val identifier: Any = SimpleDiffUtilExtension::class,
    val loadingStateListener: (isLoading: Boolean) -> Unit = {}
) : DiffUtilExtension() {

    init {
        predicate?.let {
            diffPredicate = predicate as DiffPredicate<Any>
        }
    }

    override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private lateinit var diffPredicate: DiffPredicate<Any>
    private lateinit var oldList: List<Any>
    private lateinit var newList: List<Any>
    private var diffSwapJob: Job? = null

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Position, newItemPosition: Position): Boolean {
        return diffPredicate.areItemsTheSame(
            oldList[oldItemPosition],
            newList[newItemPosition]
        )
    }

    override fun areContentsTheSame(oldItemPosition: Position, newItemPosition: Position): Boolean {
        return diffPredicate.areContentsTheSame(
            oldList[oldItemPosition],
            newList[newItemPosition]
        )
    }

    override fun setDiffPredicate(diffPredicate: DiffPredicate<*>) {
        this.diffPredicate = diffPredicate as DiffPredicate<Any>
    }

    override fun diffSwapList(newList: List<*>) {
        smartRecyclerAdapter.let { smartRecyclerAdapter ->
            this.oldList = smartRecyclerAdapter.getItems()
            this.newList = newList as MutableList<Any>
            val diffResult = DiffUtil.calculateDiff(this)
            diffResult.dispatchUpdatesTo(smartRecyclerAdapter)
            smartRecyclerAdapter.setItems(newList, false)
            smartRecyclerAdapter.updateItemCount()
        }
    }

    override fun diffSwapList(
        lifecycleScope: LifecycleCoroutineScope,
        newList: List<*>,
        callback: (Result<Boolean>) -> Unit
    ) {
        loadingStateListener.invoke(true)
        diffSwapJob?.cancel()
        this.oldList = smartRecyclerAdapter.getItems()
        this.newList = newList as MutableList<Any>
        diffSwapJob = lifecycleScope.launch(Dispatchers.IO) {
            smartRecyclerAdapter.let { smartRecyclerAdapter ->
                kotlin.runCatching {
                    val diffResult = DiffUtil.calculateDiff(this@SimpleDiffUtilExtension)
                    withContext(Dispatchers.Main) {
                        diffResult.dispatchUpdatesTo(smartRecyclerAdapter)
                        smartRecyclerAdapter.setItems(newList, false)
                        smartRecyclerAdapter.updateItemCount()
                        callback.invoke(Result.success(true))
                        loadingStateListener.invoke(false)
                    }
                }.onFailure {
                    withContext(Dispatchers.Main) {
                        callback.invoke(Result.failure(it))
                        loadingStateListener.invoke(false)
                    }
                }
            }
        }
    }

    override fun cancelDiffSwapJob() {
        diffSwapJob?.cancel()
    }

    override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter
    }
}
