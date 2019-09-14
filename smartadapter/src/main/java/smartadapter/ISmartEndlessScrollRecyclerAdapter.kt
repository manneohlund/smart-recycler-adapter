package smartadapter

/*
 * Created by Manne Öhlund on 2019-07-29.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.LayoutRes
import smartadapter.listener.OnLoadMoreListener

/**
 * Defines the extension methods for [SmartEndlessScrollRecyclerAdapter].
 */
interface ISmartEndlessScrollRecyclerAdapter {

    /**
     * Checks if endless scrolling is enabled.
     */
    var isEndlessScrollEnabled: Boolean

    /**
     * Checks if the [SmartEndlessScrollRecyclerAdapter] is in loading state.
     * Good to use when async loading takes time and user scrolls back and forth.
     */
    var isLoading: Boolean

    /**
     * Used to determine if loading view should show or not.
     * If [.isEndlessScrollEnabled] returns true this returns 1.
     */
    var endlessScrollOffset: Int

    /**
     * Enables or disables the auto load more view.
     *
     *  * Enabled state shows and indeterminate spinner.
     *  * Disabled state shows an load more button for passive activation.
     */
    var autoLoadMoreEnabled: Boolean

    /**
     * Setter for [OnLoadMoreListener] callback for listening on when the [SmartEndlessScrollRecyclerAdapter]
     * is showing the [smartadapter.viewholder.LoadMoreViewHolder].
     * @param onLoadMoreListener load more callback
     */
    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener)

    /**
     * Enables customization of the layout for the [smartadapter.viewholder.LoadMoreViewHolder].
     * @param loadMoreLayoutResource layout resource
     */
    fun setCustomLoadMoreLayoutResource(@LayoutRes loadMoreLayoutResource: Int)
}
