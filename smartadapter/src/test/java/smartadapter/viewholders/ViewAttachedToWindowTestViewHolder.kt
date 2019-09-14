package smartadapter.viewholders

/*
 * Created by Manne Öhlund on 2019-07-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import smartadapter.listener.OnViewAttachedToWindowListener
import smartadapter.listener.OnViewDetachedFromWindowListener
import smartadapter.viewholder.SmartViewHolder

open class ViewAttachedToWindowTestViewHolder(view: View) : SmartViewHolder<Any>(view),
    OnViewAttachedToWindowListener,
    OnViewDetachedFromWindowListener {

    override fun bind(item: Any) { }

    override fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) { }

    override fun onViewDetachedFromWindow(viewHolder: RecyclerView.ViewHolder) { }
}
