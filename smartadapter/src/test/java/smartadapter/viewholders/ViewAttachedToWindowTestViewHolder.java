package smartadapter.viewholders;

/*
 * Created by Manne Öhlund on 2019-07-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import smartadapter.listener.OnViewAttachedToWindowListener;
import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.viewholder.SmartViewHolder;

public class ViewAttachedToWindowTestViewHolder extends SmartViewHolder implements OnViewAttachedToWindowListener, OnViewDetachedFromWindowListener {

    public ViewAttachedToWindowTestViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Object item) {

    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }
}
