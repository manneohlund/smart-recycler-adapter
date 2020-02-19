package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import smartadapter.state.SelectionStateHolder

/**
 * Default implementation of adapter item row click listener.
 */
interface OnItemSelectedListener : OnItemClickListener {
    /**
     * Default implementation, provides and global static [SelectionStateHolder].
     */
    val selectionStateHolder: SelectionStateHolder
        get() = SelectionStateHolder()

    /**
     * Default implementation, decides the logic implementation in [smartadapter.internal.mapper.ViewEventBinderProvider]
     * for the ViewEventBinder OnItemSelectedListenerBinder.
     */
    val enableOnLongClick: Boolean
        get() = false
}
