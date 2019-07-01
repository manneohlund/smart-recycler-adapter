package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-11.
 * Copyright (c) All rights reserved.
 */

import android.util.Log;
import android.view.View;

import java.util.HashMap;

import smartadapter.R;
import smartadapter.listener.ViewEventListener;

public abstract class SmartEventViewHolder<T> extends SmartViewHolder<T> implements ViewEventHolder, OnViewEventListener {

    protected HashMap<Integer, HashMap<Integer, ViewEventListener>> viewEventListeners = new HashMap<>();

    public SmartEventViewHolder(View view) {
        super(view);
    }

    @Override
    public void setViewEventListeners(HashMap<Integer, HashMap<Integer, ViewEventListener>> viewEventListeners) {
        if (viewEventListeners == null) {
            return;
        }
        this.viewEventListeners = viewEventListeners;
    }

    @Override
    public void notifyOnViewEvent(View view, int actionId) {
        if (viewEventListeners.containsKey(view.getId())) {
            viewEventListeners.get(view.getId()).get(actionId).onViewEvent(view, actionId, getAdapterPosition());
        } else if (viewEventListeners.containsKey(R.id.undefined) && viewEventListeners.get(R.id.undefined).containsKey(R.id.undefined)) {
            viewEventListeners.get(R.id.undefined).get(R.id.undefined).onViewEvent(view, actionId, getAdapterPosition());
        } else {
            Log.e(getClass().getName(), String.format("No view event listener found for view = (%s) with actionId = (%d)", view.toString(), actionId));
        }
    }
}
