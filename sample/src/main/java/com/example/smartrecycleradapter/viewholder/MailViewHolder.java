package com.example.smartrecycleradapter.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartrecycleradapter.DemoActivity;

import smartadapter.viewholder.SmartViewHolder;

/**
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

public class MailViewHolder extends SmartViewHolder<DemoActivity.Mail> {

    public MailViewHolder(ViewGroup parentView) {
        super(LayoutInflater.from(parentView.getContext()).inflate(android.R.layout.simple_list_item_1, parentView, false));
    }

    @Override
    public void bind(DemoActivity.Mail mail) {
        ((TextView)itemView).setText(mail.toString());
    }
}
