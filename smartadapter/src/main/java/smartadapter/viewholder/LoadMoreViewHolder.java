package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-07-27.
 * Copyright (c) All rights reserved.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import smartadapter.R;

/**
 * Default implementation of load more view holder.
 */
public class LoadMoreViewHolder extends SmartViewHolder {

    private int loadingViewRes;

    public LoadMoreViewHolder(@NonNull View parentView, @LayoutRes int loadingView, boolean isAutoLoadEnabled) {
        super(LayoutInflater.from(parentView.getContext()).inflate(loadingView, (ViewGroup) parentView, false));
        loadingViewRes = loadingView;
        toggleLoading(isAutoLoadEnabled);
    }

    public void toggleLoading(boolean isLoading) {
        if (loadingViewRes == R.layout.load_more_view) {
            AppCompatButton loadMoreButton = itemView.findViewById(R.id.loadMoreButton);
            ProgressBar progressBar = itemView.findViewById(R.id.progressBar);
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
                loadMoreButton.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                loadMoreButton.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void bind(Object item) {
        // Noop
    }
}
