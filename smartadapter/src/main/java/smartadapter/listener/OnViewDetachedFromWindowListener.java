package smartadapter.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Manne Öhlund on 03/10/17.
 * Copyright © 2017. All rights reserved.
 */

public interface OnViewDetachedFromWindowListener {
    void onViewDetachedFromWindow(RecyclerView.ViewHolder holder);
}
