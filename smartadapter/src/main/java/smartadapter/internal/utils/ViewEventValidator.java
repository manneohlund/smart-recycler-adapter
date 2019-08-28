package smartadapter.internal.utils;

/*
 * Created by Manne Öhlund on 2019-06-11.
 * Copyright (c) All rights reserved.
 */

import java.util.Arrays;
import java.util.List;

import io.github.manneohlund.smartrecycleradapter.R;

/**
 * Checks if auto view events are of the predefined types provided by the SmartRecyclerAdapter library.
 */
public class ViewEventValidator {

    private static final List autoViewEvents = Arrays.asList(
            R.id.undefined,
            R.id.event_on_click,
            R.id.event_on_long_click,
            R.id.event_on_item_selected
    );

    public static boolean isViewEventIdValid(int viewEventId) {
        return autoViewEvents.contains(viewEventId);
    }
}
