package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

/**
 * Default implementation of adapter item row click listener.
 */
interface OnItemLongClickSelectedListener : OnItemSelectedListener {
    override val enableOnLongClick: Boolean
        get() = true

    override val listener: OnLongClick
}
