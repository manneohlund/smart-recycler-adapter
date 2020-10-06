package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 04/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.view.ViewGroup
import android.widget.TextView
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.models.CopyrightModel

class CopyrightViewHolder(parentView: ViewGroup) :
    SmartViewHolder<CopyrightModel>(parentView, R.layout.copyright) {

    private val summary: TextView = itemView as TextView

    override fun bind(copyrightModel: CopyrightModel) {
        summary.text = copyrightModel.toString()
    }
}
