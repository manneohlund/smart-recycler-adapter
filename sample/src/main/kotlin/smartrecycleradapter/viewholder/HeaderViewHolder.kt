package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.ViewGroup
import android.widget.TextView
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R

class HeaderViewHolder(parentView: ViewGroup) :
    SmartViewHolder<String>(parentView, R.layout.header) {

    private val summary: TextView = itemView as TextView

    override fun bind(text: String) {
        summary.text = text
    }
}
