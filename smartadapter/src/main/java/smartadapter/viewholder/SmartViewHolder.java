package smartadapter.viewholder;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import smartadapter.R;
import smartadapter.listener.ViewEventListener;

public abstract class SmartViewHolder<T> extends RecyclerView.ViewHolder {
    private ViewEventListener viewEventListener;
    private HashMap<Integer, ViewEventListener> viewEventListeners;

    public SmartViewHolder(View view) {
        super(view);
    }

    @Deprecated
    public void setViewEventListener(ViewEventListener viewEventListener) {
        this.viewEventListener = viewEventListener;
    }

    public void setViewEventListeners(HashMap<Integer, ViewEventListener> viewEventListeners) {
        if (viewEventListeners == null) {
            return;
        }

        for (Map.Entry<Integer, ViewEventListener> listenerEntry : viewEventListeners.entrySet()) {
            View targetView = itemView;
            if (listenerEntry.getKey() == R.id.undefined) {
                targetView.setOnClickListener(v -> notifyOnItemEvent(v, R.id.action_on_click));

                targetView.setOnLongClickListener(v -> {
                    notifyOnItemEvent(v, R.id.action_on_long_click);
                    return true;
                });
            } else {
                targetView = itemView.findViewById(listenerEntry.getKey());
                targetView.setOnClickListener(v -> notifyOnItemEvent(v, R.id.undefined));
            }
        }

        this.viewEventListeners = viewEventListeners;
    }

    public void notifyOnItemEvent(View view, int action) {
        if (viewEventListeners != null) {
            for (Map.Entry<Integer, ViewEventListener> listener : viewEventListeners.entrySet()) {
                if (listener.getKey() == R.id.undefined && action != R.id.undefined) {
                    listener.getValue().onViewEvent(view, action, getAdapterPosition());
                } else if (listener.getKey() == view.getId()) {
                    listener.getValue().onViewEvent(view, R.id.action_on_click, getAdapterPosition());
                }
            }
        }
    }

    public abstract void bind(T item);
}