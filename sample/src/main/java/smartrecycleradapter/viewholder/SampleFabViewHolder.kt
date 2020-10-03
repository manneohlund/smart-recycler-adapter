package smartrecycleradapter.viewholder

/*
 * Created by Manne Öhlund on 2019-08-04.
 * Copyright (c) All rights reserved.
 */

import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R

class SampleFabViewHolder(parentView: ViewGroup) :
    SmartViewHolder<SampleFabViewHolder.SimpleFabItem>(parentView, R.layout.efab) {

    private val fab: ExtendedFloatingActionButton = itemView.findViewById(R.id.fabItem)

    override fun bind(item: SimpleFabItem) {
        fab.setIconResource(item.icon)
        fab.text = item.title
    }

    class SimpleFabItem(val icon: Int, val title: String)
}
