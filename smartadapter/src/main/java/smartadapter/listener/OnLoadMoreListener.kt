package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright © 2019. All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView
import smartadapter.viewholder.LoadMoreViewHolder

/**
 * Callback for [smartadapter.SmartEndlessScrollRecyclerAdapter] to intercept when the adapter
 * has scrolled to the last item. The [smartadapter.viewholder.LoadMoreViewHolder] will show without any
 * mapped data type.
 * Extends [OnViewAttachedToWindowListener].
 */
interface OnLoadMoreListener : OnViewAttachedToWindowListener {

    override fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) {
        onLoadMore(viewHolder as LoadMoreViewHolder)
    }

    /**
     * Called when [smartadapter.viewholder.LoadMoreViewHolder] has been attached to the window.
     * @param loadMoreViewHolder view holder
     */
    fun onLoadMore(loadMoreViewHolder: LoadMoreViewHolder)
}

inline fun onLoadMoreListener(crossinline onLoadMore: (loadMoreViewHolder: LoadMoreViewHolder) -> Unit) = object : OnLoadMoreListener {
    override fun onLoadMore(loadMoreViewHolder: LoadMoreViewHolder) {
        onLoadMore(loadMoreViewHolder)
    }
}