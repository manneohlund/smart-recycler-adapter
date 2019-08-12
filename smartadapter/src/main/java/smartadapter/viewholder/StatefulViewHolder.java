package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-08-10.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;

import smartadapter.state.SmartStateHolder;

/**
 * Implements and lets your {@link SmartViewHolder} extension handle adapter item position states.
 * @see smartadapter.state.SelectionStateHolder
 * @param <T> {@link SmartStateHolder} extension such as {@link smartadapter.state.SelectionStateHolder}
 */
public interface StatefulViewHolder<T extends SmartStateHolder> {

    /**
     * Called when the view holder is created
     * @param selectionStateHolder state holder
     */
    void setStateHolder(@NonNull T selectionStateHolder);
}
