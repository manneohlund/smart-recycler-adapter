package com.example.smartrecycleradapter.extension;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreCachingLinearLayoutManager extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 600;
    private int extraLayoutSpace = -1;
    private Context context;

    public PreCachingLinearLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public PreCachingLinearLayoutManager(Context context, int extraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public PreCachingLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }

    public static PreCachingLinearLayoutManager getInstance(Activity activity) {
        //Setup layout manager
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        int height = size.y;
        PreCachingLinearLayoutManager preCachingLinearLayoutManager = new PreCachingLinearLayoutManager(activity);
        preCachingLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        preCachingLinearLayoutManager.setExtraLayoutSpace(height*2);
        return preCachingLinearLayoutManager;
    }
}
