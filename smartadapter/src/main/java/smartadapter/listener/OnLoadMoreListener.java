package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright © 2019. All rights reserved.
 */

/**
 * Callback for {@link smartadapter.SmartEndlessScrollRecyclerAdapter} to intercept when the adapter
 * has scrolled to the last item. The {@link smartadapter.viewholder.LoadMoreViewHolder} will show without any
 * mapped data type.
 * Extends {@link OnViewAttachedToWindowListener}.
 */
public interface OnLoadMoreListener extends OnViewAttachedToWindowListener {

    @Override
    default void onViewAttachedToWindow() {
        onLoadMore();
    }

    /**
     * Called when {@link smartadapter.viewholder.LoadMoreViewHolder} has been attached to the window.
     */
    void onLoadMore();
}
