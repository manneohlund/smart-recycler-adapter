package com.example.smartrecycleradapter.viewholder;

import android.graphics.Color;
import android.view.ViewGroup;

import com.example.smartrecycleradapter.DemoActivity;
import com.example.smartrecycleradapter.R;

import static android.view.View.GONE;

/**
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

public class WarningPostViewHolder extends PostViewHolder {

    public WarningPostViewHolder(ViewGroup parentView) {
        super(parentView);
        itemView.findViewById(R.id.more).setVisibility(GONE);
    }

    @Override
    public void bind(DemoActivity.Post mail) {
        super.bind(mail);
        title.setTextColor(Color.YELLOW);
    }
}
