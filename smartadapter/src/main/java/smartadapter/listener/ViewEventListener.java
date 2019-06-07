package smartadapter.listener;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View;

public interface ViewEventListener {
    void onViewEvent(View view, int actionId, int position);
}
