package smartadapter.viewholders;

/*
 * Created by Manne Öhlund on 2019-07-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import smartadapter.viewholder.RecyclableViewHolder;
import smartadapter.viewholder.SmartViewHolder;

public class RecyclableTestViewHolder extends SmartViewHolder implements RecyclableViewHolder {

    public RecyclableTestViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Object item) {

    }

    @Override
    public boolean onFailedToRecycleView() {
        return true;
    }
}
