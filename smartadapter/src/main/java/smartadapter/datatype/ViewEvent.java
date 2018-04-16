package smartadapter.datatype;

/**
 * Created by Manne Öhlund on 2018-04-12.
 * Copyright © 2018 Frost. All rights reserved.
 */
public class ViewEvent {
    public static final int NO_EVENT = 0;
    public static final int ON_CLICK = 1;
    public static final int ON_LONG_CLICK = 1 << 1;

    public static boolean isOnClickSet(int events) {
        return (events & ON_CLICK) == ON_CLICK;
    }

    public static boolean isOnLongClickSet(int events) {
        return (events & ON_LONG_CLICK) == ON_LONG_CLICK;
    }
}
