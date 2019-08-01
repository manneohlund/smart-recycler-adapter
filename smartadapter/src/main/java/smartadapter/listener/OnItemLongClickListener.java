package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import smartadapter.R;

/**
 * Default implementation of adapter item row long click listener.
 */
public interface OnItemLongClickListener extends OnViewActionListener {
    @Override
    default int getViewActionId() {
        return R.id.action_on_long_click;
    }
}
