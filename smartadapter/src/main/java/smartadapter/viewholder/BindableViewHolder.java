package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-07-21.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView;

/**
 * Contains basic logic methods {@link #bind(Object)} and {@link #unbind()} for the {@link SmartViewHolder}.
 * @param <T> Data item
 */
public interface BindableViewHolder<T> {

    /**
     * Called when a {@link SmartViewHolder} is created or recycled from {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     * @param item data
     */
    void bind(T item);

    /**
     * Called when {@link SmartViewHolder} is recycled in {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}.
     * Default implementation has no operation.
     */
    default void unbind() {
        // No op
    }
}
