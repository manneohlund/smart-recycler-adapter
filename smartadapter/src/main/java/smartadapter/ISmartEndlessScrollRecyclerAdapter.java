package smartadapter;

/*
 * Created by Manne Öhlund on 2019-07-29.
 * Copyright (c) All rights reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import smartadapter.listener.OnLoadMoreListener;

/**
 * Defines the extension methods for {@link SmartEndlessScrollRecyclerAdapter}.
 */
public interface ISmartEndlessScrollRecyclerAdapter {

    /**
     * Used to determine if loading view should show or not.
     * If {@link #isEndlessScrollEnabled()} returns true this returns 1.
     * @return offset loading view count
     */
    int getEndlessScrollOffset();

    /**
     * Checks if endless scrolling is enabled.
     * @return endless scrolling enabled
     */
    boolean isEndlessScrollEnabled();

    /**
     * Setter for enabling endless scrolling by letting the {@link SmartEndlessScrollRecyclerAdapter} render a loading view.
     * @param enabled if true enables endless scrolling.
     */
    void setEndlessScrollEnabled(boolean enabled);

    /**
     * Checks if the {@link SmartEndlessScrollRecyclerAdapter} is in loading state.
     * Good to use when async loading takes time and user scrolls back and forth.
     * @return loading state
     */
    boolean isLoading();

    /**
     * Setting the loading state to {@link SmartEndlessScrollRecyclerAdapter}.
     * @param loading state for loading more items
     */
    void setIsLoading(boolean loading);

    /**
     * Setter for {@link OnLoadMoreListener} callback for listening on when the {@link SmartEndlessScrollRecyclerAdapter}
     * is showing the {@link smartadapter.viewholder.LoadMoreViewHolder}.
     * @param onLoadMoreListener load more callback
     */
    void setOnLoadMoreListener(@NonNull OnLoadMoreListener onLoadMoreListener);

    /**
     * Enables customization of the layout for the {@link smartadapter.viewholder.LoadMoreViewHolder}.
     * @param loadMoreLayoutResource layout resource
     */
    void setCustomLoadMoreLayoutResource(@LayoutRes int loadMoreLayoutResource);
}
