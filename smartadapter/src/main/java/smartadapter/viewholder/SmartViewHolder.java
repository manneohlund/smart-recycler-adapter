package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2019 All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import smartadapter.R;
import smartadapter.listener.ViewEventListener;

/**
 * Extension of {@link RecyclerView.ViewHolder} containing data item binding method and view event listeners.
 * @param <T> Data item type
 */
public abstract class SmartViewHolder<T> extends RecyclerView.ViewHolder {

    /**
     * Map(ViewHolder, Map(ViewEventId, ViewEventListener))
     */
    private HashMap<Integer, HashMap<Integer, ViewEventListener>> viewEventListeners = new HashMap<>();

    public SmartViewHolder(View view) {
        super(view);
    }

    public HashMap<Integer, HashMap<Integer, ViewEventListener>> getViewEventListeners() {
        return viewEventListeners;
    }

    public Collection<ViewEventListener> getViewEventListener(int viewId) {
        HashMap<Integer, ViewEventListener> pair = viewEventListeners.get(viewId);
        if (pair == null) {
            return null;
        }
        return pair.values();
    }

    public void setViewEventListeners(HashMap<Integer, HashMap<Integer, ViewEventListener>> viewEventListeners) {
        if (viewEventListeners == null) {
            return;
        }

        for (Map.Entry<Integer, HashMap<Integer, ViewEventListener>> listenerEntry : viewEventListeners.entrySet()) {
            View targetView = itemView;
            int eventViewId = listenerEntry.getKey();
            if (eventViewId != R.id.undefined) {
                targetView = itemView.findViewById(eventViewId);
            }

            for (Map.Entry<Integer, ViewEventListener> viewEventEntry : listenerEntry.getValue().entrySet()) {
                int viewEventId = viewEventEntry.getKey();
                ViewEventListener viewEventListener = viewEventEntry.getValue();

                if (viewEventId == R.id.action_on_click) {
                    targetView.setOnClickListener(v -> viewEventListener.onViewEvent(v, viewEventId, getAdapterPosition()));
                } else if (viewEventId == R.id.action_on_long_click) {
                    targetView.setOnLongClickListener(v -> {
                        viewEventListener.onViewEvent(v, viewEventId, getAdapterPosition());
                        return true;
                    });
                }
            }

            this.viewEventListeners.put(eventViewId, listenerEntry.getValue());
        }
    }

    protected void notifyOnItemEvent(View view, int actionId) {
        if (viewEventListeners.containsKey(view.getId())) {
            viewEventListeners.get(view.getId()).get(actionId).onViewEvent(view, actionId, getAdapterPosition());
        } else if (viewEventListeners.containsKey(R.id.undefined) && viewEventListeners.get(R.id.undefined).containsKey(R.id.undefined)) {
            viewEventListeners.get(R.id.undefined).get(R.id.undefined).onViewEvent(view, actionId, getAdapterPosition());
        } else {
            Log.e(getClass().getName(), String.format("No view event listener found for view = (%s) with actionId = (%d)", view.toString(), actionId));
        }
    }

    public abstract void bind(T item);
}