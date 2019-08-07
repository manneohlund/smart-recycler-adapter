package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-10.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Extension of {@link RecyclerView.ViewHolder} containing data item binding method.
 * @param <T> Data item type
 */
public abstract class SmartViewHolder<T>
        extends RecyclerView.ViewHolder
        implements BindableViewHolder<T> {

    public SmartViewHolder(View view) {
        super(view);
    }
}