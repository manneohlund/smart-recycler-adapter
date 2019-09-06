package smartadapter.viewholder

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import smartadapter.state.SmartStateHolder

/**
 * Implements and lets your [SmartViewHolder] extension handle adapter item position states.
 * @see smartadapter.state.SelectionStateHolder
 *
 * @param <T> [SmartStateHolder] extension such as [smartadapter.state.SelectionStateHolder]
</T> */
interface StatefulViewHolder<T : SmartStateHolder> {

    /**
     * Holds the reference to an SmartStateHolder extension
     */
    var stateHolder: T
}
