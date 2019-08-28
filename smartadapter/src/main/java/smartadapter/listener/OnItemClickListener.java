package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import io.github.manneohlund.smartrecycleradapter.R;

/**
 * Default implementation of adapter item row click listener.
 */
public interface OnItemClickListener extends OnViewEventListener {
    @Override
    default int getViewEventId() {
        return R.id.event_on_click;
    }
}
