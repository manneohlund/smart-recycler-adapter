package smartadapter.internal.utils

/*
 * Created by Manne Öhlund on 2019-06-11.
 * Copyright (c) All rights reserved.
 */

import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnViewEventListener

/**
 * Checks if auto view events are of the predefined types provided by the SmartRecyclerAdapter library.
 */
@Deprecated("Use custom view event binder")
object ViewEventValidator {

    private val autoViewEvents = listOf(
        OnItemClickListener::class,
        OnItemLongClickListener::class
    )

    fun isViewEventIdValid(viewEventId: OnViewEventListener<*>): Boolean {
        return autoViewEvents.contains(viewEventId::class)
    }
}
