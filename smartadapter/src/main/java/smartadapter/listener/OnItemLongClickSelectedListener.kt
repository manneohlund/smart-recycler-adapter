package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

/**
 * Default implementation of adapter item row click listener.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
interface OnItemLongClickSelectedListener : OnItemSelectedListener {
    override val enableOnLongClick: Boolean
        get() = true
}
