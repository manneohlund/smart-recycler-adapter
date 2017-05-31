package smartadapter.viewholder;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import smartadapter.listener.ViewEventListener;

public abstract class SmartViewHolder<T> extends RecyclerView.ViewHolder {

    private ViewEventListener viewEventListener;

    public SmartViewHolder(View view) {
        super(view);
    }

    public void setViewEventListener(ViewEventListener viewEventListener) {
        this.viewEventListener = viewEventListener;
    }

    public void notifyOnItemEvent(View view, int action) {
        if (viewEventListener != null) {
            viewEventListener.onViewEvent(view, action, getAdapterPosition());
        }
    }

    public abstract void bind(T item);
}