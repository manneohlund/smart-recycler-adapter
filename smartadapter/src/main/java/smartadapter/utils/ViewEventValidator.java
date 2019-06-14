package smartadapter.utils;

/*
 * Created by Manne Öhlund on 2019-06-11.
 * Copyright (c) All rights reserved.
 */

import java.util.Arrays;
import java.util.List;

import smartadapter.R;

public class ViewEventValidator {

    private static final List autoViewEvents = Arrays.asList(
            R.id.undefined,
            R.id.action_on_click,
            R.id.action_on_long_click
    );

    public static boolean isViewEventIdValid(int viewEventId) {
        return autoViewEvents.contains(viewEventId);
    }
}
