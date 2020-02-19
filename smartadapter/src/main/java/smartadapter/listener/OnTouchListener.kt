package smartadapter.listener

import android.view.MotionEvent
import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter


/*
 * Created by Manne Öhlund on 2020-02-03.
 * Copyright (c) All rights reserved.
 */

typealias OnTouch = (View, MotionEvent, SmartRecyclerAdapter, Position) -> Boolean

interface OnTouchListener  : OnViewEventListener<OnTouch> {
    override val listener: OnTouch
}