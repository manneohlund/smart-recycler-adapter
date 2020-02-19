package smartadapter.listener

import android.widget.RadioGroup
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter


/*
 * Created by Manne Öhlund on 2020-02-03.
 * Copyright (c) All rights reserved.
 */

typealias CheckedId = Int

typealias OnCheckedChange = (RadioGroup, CheckedId, SmartRecyclerAdapter, Position) -> Unit

interface OnCheckedChangeListener : OnViewEventListener<OnCheckedChange> {
    override val listener: OnCheckedChange
}