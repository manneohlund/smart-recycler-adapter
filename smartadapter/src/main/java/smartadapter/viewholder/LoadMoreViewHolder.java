package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-07-27.
 * Copyright (c) All rights reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Default implementation of load more view holder.
 */
public class LoadMoreViewHolder extends SmartViewHolder {

    public LoadMoreViewHolder(@NonNull View itemView, @LayoutRes int loadingView) {
        super(LayoutInflater.from(itemView.getContext()).inflate(loadingView, (ViewGroup) itemView, false));
    }

    @Override
    public void bind(Object item) {
        // Noop
    }
}
