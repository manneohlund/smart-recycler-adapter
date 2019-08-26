package io.github.manneohlund.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.viewholder.SmartViewHolder

class HeaderViewHolder(parentView: ViewGroup) : SmartViewHolder<String>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.header, parentView, false)) {

    private val summary: TextView = itemView as TextView

    override fun bind(text: String) {
        summary.text = text
    }
}
