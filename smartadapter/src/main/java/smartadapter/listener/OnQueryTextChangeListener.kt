package smartadapter.listener

import android.view.View
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter


/*
 * Created by Manne Öhlund on 2020-02-03.
 * Copyright (c) All rights reserved.
 */

enum class QueryTextType {
    SUBMIT,
    CHANGE
}

typealias QueryText = String?

typealias OnQueryTextListener = (View, SmartRecyclerAdapter, Position) -> (QueryText?, QueryTextType) -> Boolean

interface OnQueryTextChangeListener : OnViewEventListener<OnQueryTextListener> {
    override val listener: OnQueryTextListener
}