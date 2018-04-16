package smartadapter.viewholder;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import smartadapter.R;
import smartadapter.datatype.ViewEvent;
import smartadapter.listener.ViewEventListener;

public abstract class SmartViewHolder<T> extends RecyclerView.ViewHolder {

    /**
     * HashMap(ViewHolder, Pair(ViewEvents, ViewEventListener))
     */
    private HashMap<Integer, Pair<Integer, ViewEventListener>> viewEventListeners = new HashMap<>();

    public SmartViewHolder(View view) {
        super(view);
    }

    public HashMap<Integer, Pair<Integer, ViewEventListener>> getViewEventListeners() {
        return viewEventListeners;
    }

    public ViewEventListener getViewEventListener(int viewId) {
        Pair<Integer, ViewEventListener> pair = viewEventListeners.get(viewId);
        if (pair == null) {
            return null;
        }
        return pair.second;
    }

    public void setViewEventListeners(HashMap<Integer, Pair<Integer, ViewEventListener>> viewEventListeners) {
        if (viewEventListeners == null) {
            return;
        }

        for (Map.Entry<Integer, Pair<Integer, ViewEventListener>> listenerEntry : viewEventListeners.entrySet()) {
            View targetView = itemView;
            int eventViewId = listenerEntry.getKey();
            if (eventViewId != R.id.undefined) {
                targetView = itemView.findViewById(eventViewId);
            }

            int autoViewEvents = listenerEntry.getValue().first;

            if (ViewEvent.isOnClickSet(autoViewEvents)) {
                targetView.setOnClickListener(v -> smartNotifyOnItemEvent(v, eventViewId, R.id.action_on_click));
            }
            if (ViewEvent.isOnLongClickSet(autoViewEvents)) {
                targetView.setOnLongClickListener(v -> {
                    smartNotifyOnItemEvent(v, eventViewId, R.id.action_on_long_click);
                    return true;
                });
            }

            this.viewEventListeners.put(eventViewId, listenerEntry.getValue());
        }
    }

    private void smartNotifyOnItemEvent(View view, int eventViewId, int action) {
        viewEventListeners.get(eventViewId).second.onViewEvent(view, action, getAdapterPosition());
    }

    public void notifyOnItemEvent(View view, int action) {
        viewEventListeners.get(view.getId()).second.onViewEvent(view, action, getAdapterPosition());
    }

    public abstract void bind(T item);
}