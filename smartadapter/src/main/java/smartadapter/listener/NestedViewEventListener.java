package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-06-26.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import smartadapter.SmartRecyclerAdapter;

public abstract class NestedViewEventListener implements ViewEventListener {

    private SmartRecyclerAdapter smartRecyclerAdapter;

    public void setSmartRecyclerAdapter(SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    public abstract void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position);

    @Override
    public void onViewEvent(View view, int actionId, int position) {
        onViewEvent(smartRecyclerAdapter, view, actionId, position);
    }
}
