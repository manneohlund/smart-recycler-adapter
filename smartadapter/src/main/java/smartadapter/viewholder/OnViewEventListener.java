package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-10.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

public interface OnViewEventListener {
    void notifyOnViewEvent(View view, int actionId);
}
