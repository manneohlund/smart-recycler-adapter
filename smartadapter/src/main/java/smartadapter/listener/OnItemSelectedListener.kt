package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.state.SelectionStateHolder

/**
 * Default implementation of adapter item row click listener.
 */
interface OnItemSelectedListener : OnItemClickListener {

    override val viewEventId: ViewEventId
        get() = R.id.event_on_item_selected

    /**
     * Default implementation, provides and global static [SelectionStateHolder].
     * @return selectionStateHolder
     */
    val selectionStateHolder: SelectionStateHolder
        get() = SelectionStateHolder()

    /**
     * Default implementation, decides the logic implementation in [smartadapter.internal.mapper.ViewEventBinderProvider]
     * for the ViewEventBinder OnItemSelectedListenerBinder.
     * @return default false
     */
    val enableOnLongClick: Boolean
        get() = false
}
