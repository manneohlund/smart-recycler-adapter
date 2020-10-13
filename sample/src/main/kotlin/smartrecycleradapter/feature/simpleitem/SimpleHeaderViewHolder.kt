package smartrecycleradapter.feature.simpleitem

import android.view.ViewGroup
import android.widget.TextView
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R

/*
 * Created by Manne Öhlund on 2019-08-04.
 * Copyright (c) All rights reserved.
 */

open class SimpleHeaderViewHolder<T : Any>(parentView: ViewGroup) :
    SmartViewHolder<T>(parentView, R.layout.simple_item) {

    init {
        itemView.setBackgroundColor(color)
    }

    override fun bind(item: T) {
        (itemView as TextView).text = "Header $item"
    }

    companion object {
        var color = (Math.random() * 16777215).toInt() or (0xFF shl 24)
    }
}
