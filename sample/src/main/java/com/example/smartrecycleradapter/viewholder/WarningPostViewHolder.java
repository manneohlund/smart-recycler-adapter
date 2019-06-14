package com.example.smartrecycleradapter.viewholder;

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.smartrecycleradapter.DemoActivity;
import com.example.smartrecycleradapter.R;

import static android.view.View.GONE;

public class WarningPostViewHolder extends PostViewHolder {

    public WarningPostViewHolder(ViewGroup parentView) {
        super(parentView);
        itemView.findViewById(R.id.more).setVisibility(GONE);
        itemView.findViewById(R.id.switch_button).setVisibility(View.VISIBLE);
        Switch switchButton = itemView.findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) ->
                notifyOnViewEvent(buttonView, R.id.action_on_check_change));
    }

    @Override
    public void bind(DemoActivity.Post mail) {
        super.bind(mail);
        title.setTextColor(Color.YELLOW);
    }
}
