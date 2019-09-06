package smartadapter

/*
 * Created by Manne Öhlund on 2019-07-27.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.listener.OnLoadMoreListener
import smartadapter.viewholder.LoadMoreViewHolder
import smartadapter.viewholder.SmartViewHolder

/**
 * Enables endless scrolling or pagination. Let's the adapter show a [LoadMoreViewHolder] when scrolled to last item.
 */
@Suppress("UNCHECKED_CAST")
class SmartEndlessScrollRecyclerAdapter(items: MutableList<Any>) : SmartRecyclerAdapter(items), ISmartEndlessScrollRecyclerAdapter {

    private val VIEW_TYPE_LOADING = Integer.MAX_VALUE

    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var endlessScrollEnabled = true
    private var autoLoadMoreEnabled = true
    private var loading = false
    private var loadMoreLayoutResource = R.layout.load_more_view

    override fun getItemViewType(position: Int): Int {
        return if (isEndlessScrollEnabled && position == itemCount - endlessScrollOffset) {
            VIEW_TYPE_LOADING
        } else super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartViewHolder<Any> {
        return if (viewType == VIEW_TYPE_LOADING) {
            LoadMoreViewHolder(parent, loadMoreLayoutResource, autoLoadMoreEnabled)
        } else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: SmartViewHolder<Any>, position: Int) {
        if (position < itemCount - endlessScrollOffset) {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + endlessScrollOffset
    }

    override fun onViewAttachedToWindow(holder: SmartViewHolder<Any>) {
        super.onViewAttachedToWindow(holder)
        if (holder is LoadMoreViewHolder) {
            if (autoLoadMoreEnabled) {
                onLoadMoreListener!!.onLoadMore(holder)
            } else {
                holder.toggleLoading(false)
                holder.itemView.findViewById<View>(R.id.loadMoreButton).setOnClickListener { v ->
                    onLoadMoreListener!!.onLoadMore(holder)
                    holder.toggleLoading(true)
                }
            }
        }
    }

    override fun getEndlessScrollOffset(): Int {
        return if (isEndlessScrollEnabled) 1 else 0
    }

    override fun isEndlessScrollEnabled(): Boolean {
        return endlessScrollEnabled
    }

    override fun setEndlessScrollEnabled(enabled: Boolean) {
        this.endlessScrollEnabled = enabled
        smartNotifyItemChanged(itemCount)
    }

    override fun setAutoLoadMore(enabled: Boolean) {
        this.autoLoadMoreEnabled = enabled
    }

    override fun isLoading(): Boolean {
        return loading
    }

    override fun setIsLoading(loading: Boolean) {
        this.loading = loading
    }

    override fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    override fun setCustomLoadMoreLayoutResource(@LayoutRes loadMoreLayoutResource: Int) {
        this.loadMoreLayoutResource = loadMoreLayoutResource
    }

    companion object {

        /**
         * Builder of [SmartRecyclerAdapter] for easy implementation.
         * @return SmartAdapterBuilder
         */
        fun items(items: MutableList<*>): SmartAdapterBuilder {
            return SmartAdapterBuilder(SmartEndlessScrollRecyclerAdapter(items as MutableList<Any>))
        }

        /**
         * Builder of [SmartRecyclerAdapter] for easy implementation.
         * @return SmartAdapterBuilder
         */
        fun empty(): SmartAdapterBuilder {
            return SmartAdapterBuilder(SmartEndlessScrollRecyclerAdapter(mutableListOf()))
        }
    }
}
