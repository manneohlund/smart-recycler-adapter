package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter

/**
 * Default implementation of adapter item row click listener.
 */
typealias OnClick = (View, SmartRecyclerAdapter, Position) -> Unit

interface OnItemClickListener : OnViewEventListener<OnClick> {
    override val listener: OnClick
}