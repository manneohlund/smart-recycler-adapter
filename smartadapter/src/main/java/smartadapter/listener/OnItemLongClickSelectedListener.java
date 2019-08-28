package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

/**
 * Default implementation of adapter item row click listener.
 */
public interface OnItemLongClickSelectedListener extends OnItemSelectedListener {

    @Override
    default boolean enableOnLongClick() {
        return true;
    }
}
