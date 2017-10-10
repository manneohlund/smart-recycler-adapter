package com.example.smartrecycleradapter.viewholder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartrecycleradapter.DemoActivity;

/**
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

public class WarningMailViewHolder extends MailViewHolder {

    public WarningMailViewHolder(ViewGroup parentView) {
        super(parentView);
    }

    @Override
    public void bind(DemoActivity.Mail mail) {
        super.bind(mail);
        ((TextView)itemView).setTextColor(Color.YELLOW);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyOnItemEvent(view, 1);
            }
        });
    }
}
