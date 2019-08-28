package smartadapter;

/*
 * Created by Manne Öhlund on 2019-07-27.
 * Copyright (c) All rights reserved.
 */

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.github.manneohlund.smartrecycleradapter.R;
import smartadapter.listener.OnLoadMoreListener;
import smartadapter.viewholder.LoadMoreViewHolder;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Enables endless scrolling or pagination. Let's the adapter show a {@link LoadMoreViewHolder} when scrolled to last item.
 */
public class SmartEndlessScrollRecyclerAdapter extends SmartRecyclerAdapter implements ISmartEndlessScrollRecyclerAdapter {

    private final int VIEW_TYPE_LOADING = Integer.MAX_VALUE;

    private OnLoadMoreListener onLoadMoreListener;
    private boolean endlessScrollEnabled = true;
    private boolean autoLoadMoreEnabled = true;
    private boolean loading = false;
    private int loadMoreLayoutResource = R.layout.load_more_view;

    public SmartEndlessScrollRecyclerAdapter(Object callerEnclosingClass, @NonNull List items) {
        super(callerEnclosingClass, items);
    }

    @Override
    public int getItemViewType(int position) {
        if (isEndlessScrollEnabled() && position == getItemCount() - getEndlessScrollOffset()) {
            return VIEW_TYPE_LOADING;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return new LoadMoreViewHolder(parent, loadMoreLayoutResource, autoLoadMoreEnabled);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder holder, int position) {
        if (position < getItemCount() - getEndlessScrollOffset()) {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + getEndlessScrollOffset();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SmartViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof LoadMoreViewHolder) {
            if (autoLoadMoreEnabled) {
                onLoadMoreListener.onLoadMore((LoadMoreViewHolder) holder);
            } else {
                ((LoadMoreViewHolder)holder).toggleLoading(false);
                holder.itemView.findViewById(R.id.loadMoreButton).setOnClickListener(v -> {
                    onLoadMoreListener.onLoadMore((LoadMoreViewHolder) holder);
                    ((LoadMoreViewHolder)holder).toggleLoading(true);
                });
            }
        }
    }

    @Override
    public int getEndlessScrollOffset() {
        return isEndlessScrollEnabled() ? 1 : 0;
    }

    @Override
    public boolean isEndlessScrollEnabled() {
        return endlessScrollEnabled;
    }

    @Override
    public void setEndlessScrollEnabled(boolean enabled) {
        this.endlessScrollEnabled = enabled;
        smartNotifyItemChanged(getItemCount());
    }

    @Override
    public void setAutoLoadMore(boolean enabled) {
        this.autoLoadMoreEnabled = enabled;
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public void setIsLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void setOnLoadMoreListener(@NonNull OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void setCustomLoadMoreLayoutResource(@LayoutRes int loadMoreLayoutResource) {
        this.loadMoreLayoutResource = loadMoreLayoutResource;
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation.
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder items(List items) {
        return new SmartAdapterBuilder(new SmartEndlessScrollRecyclerAdapter(null, items));
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation.
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder empty() {
        return new SmartAdapterBuilder(new SmartEndlessScrollRecyclerAdapter(null, new ArrayList()));
    }
}
