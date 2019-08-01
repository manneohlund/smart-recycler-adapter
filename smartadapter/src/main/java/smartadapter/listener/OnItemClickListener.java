package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import smartadapter.R;

/**
 * Default implementation of adapter item row click listener.
 */
public interface OnItemClickListener extends OnViewActionListener {
    @Override
    default int getViewActionId() {
        return R.id.action_on_click;
    }
}
