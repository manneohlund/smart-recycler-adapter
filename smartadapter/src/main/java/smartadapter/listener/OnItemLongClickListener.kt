package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Default implementation of adapter item row long click listener.
 */
typealias OnLongClick = (View, SmartRecyclerAdapter, Position) -> Unit

interface OnItemLongClickListener : OnViewEventListener<OnLongClick> {
    override val listener: OnLongClick
}