package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import io.github.manneohlund.smartrecycleradapter.R;
import smartadapter.state.SelectionStateHolder;

/**
 * Default implementation of adapter item row click listener.
 */
public interface OnItemSelectedListener extends OnItemClickListener {
    SelectionStateHolder selectionStateHolder = new SelectionStateHolder();

    /**
     * Default implementation, provides and global static {@link SelectionStateHolder}.
     * @return selectionStateHolder
     */
    default SelectionStateHolder getSelectionStateHolder() {
        return selectionStateHolder;
    }

    /**
     * Default implementation, decides the logic implementation in {@link smartadapter.internal.mapper.ViewEventBinderProvider}
     * for the ViewEventBinder OnItemSelectedListenerBinder.
     * @return default false
     */
    default boolean enableOnLongClick() {
        return false;
    }

    @Override
    default int getViewEventId() {
        return R.id.event_on_item_selected;
    }
}
