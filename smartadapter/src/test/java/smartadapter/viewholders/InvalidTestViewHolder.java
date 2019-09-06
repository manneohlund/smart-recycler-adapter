package smartadapter.viewholders;

/*
 * Created by Manne Öhlund on 2019-07-17.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import androidx.annotation.NonNull;

import smartadapter.viewholder.SmartViewHolder;

public class InvalidTestViewHolder extends SmartViewHolder {

    public InvalidTestViewHolder(View view, int invalidParam) {
        super(view);
    }

    @Override
    public void bind(@NonNull Object item) {

    }
}