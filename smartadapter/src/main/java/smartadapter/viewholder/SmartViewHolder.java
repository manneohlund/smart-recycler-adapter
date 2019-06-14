package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-10.
 * Copyright (c) All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Extension of {@link RecyclerView.ViewHolder} containing data item binding method.
 * @param <T> Data item type
 */
public abstract class SmartViewHolder<T> extends RecyclerView.ViewHolder {

    public SmartViewHolder(View view) {
        super(view);
    }

    public abstract void bind(T item);
}