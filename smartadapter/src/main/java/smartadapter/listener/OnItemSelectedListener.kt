package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.ViewEventId
import smartadapter.state.SelectionStateHolder

/**
 * Default global state holder that runs in application scope.
 */
val defaultGlobalStateHolder = SelectionStateHolder()

/**
 * Default implementation of adapter item row click listener.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
interface OnItemSelectedListener : OnItemClickListener {

    override val viewEventId: ViewEventId
        get() = R.id.event_on_item_selected

    /**
     * Default implementation, provides and global static [SelectionStateHolder].
     */
    val selectionStateHolder: SelectionStateHolder
        get() = defaultGlobalStateHolder

    /**
     * Default implementation, decides the logic implementation in [smartadapter.internal.mapper.ViewEventBinderProvider]
     * for the ViewEventBinder OnItemSelectedListenerBinder.
     */
    val enableOnLongClick: Boolean
        get() = false
}
