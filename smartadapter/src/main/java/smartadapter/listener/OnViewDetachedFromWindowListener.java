package smartadapter.listener;

/*
 * Created by Manne Öhlund on 03/10/17.
 * Copyright © 2017. All rights reserved.
 */

import android.support.v7.widget.RecyclerView;

public interface OnViewDetachedFromWindowListener {
    void onViewDetachedFromWindow(RecyclerView.ViewHolder holder);
}
