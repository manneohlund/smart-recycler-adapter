package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import io.github.manneohlund.smartrecycleradapter.R

/**
 * Default implementation of adapter item row click listener.
 */
interface OnItemClickListener : OnViewEventListener {
    override val viewEventId: ViewEventId
        get() = R.id.event_on_click
}

inline fun onItemClickListener(crossinline viewEvent: (
        view: View,
        viewEventId: ViewEventId,
        position: Position) -> Unit) = object : OnItemClickListener {
    override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
        viewEvent(view, viewEventId, position)
    }
}