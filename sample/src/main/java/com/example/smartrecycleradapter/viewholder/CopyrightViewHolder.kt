package com.example.smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.models.CopyrightModel
import smartadapter.viewholder.SmartAutoEventViewHolder

class CopyrightViewHolder(parentView: ViewGroup) : SmartAutoEventViewHolder<CopyrightModel>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.copyright, parentView, false)) {

    private val summary: TextView = itemView as TextView

    override fun bind(copyrightModel: CopyrightModel) {
        summary.text = copyrightModel.toString()
    }
}
