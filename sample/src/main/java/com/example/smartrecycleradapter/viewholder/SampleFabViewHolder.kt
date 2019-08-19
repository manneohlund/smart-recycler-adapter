package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-08-04.
 * Copyright (c) All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.smartrecycleradapter.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import smartadapter.viewholder.SmartViewHolder

class SampleFabViewHolder(parentView: ViewGroup) : SmartViewHolder<SampleFabViewHolder.SimpleFabItem>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.efab, parentView, false)) {

    private val fab: ExtendedFloatingActionButton = itemView.findViewById(R.id.fabItem)

    override fun bind(item: SimpleFabItem) {
        fab.setIconResource(item.icon)
        fab.text = item.title
    }

    class SimpleFabItem(val icon: Int, val title: String)
}
