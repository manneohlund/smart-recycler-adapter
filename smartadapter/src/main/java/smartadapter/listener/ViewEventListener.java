package smartadapter.listener;

import android.view.View;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

public interface ViewEventListener {
    void onViewEvent(View view, int actionId, int position);
}
