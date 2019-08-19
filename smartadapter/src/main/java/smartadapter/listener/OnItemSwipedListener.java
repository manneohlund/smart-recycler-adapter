package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Listener for swipe callbacks from extensions of {@link smartadapter.widget.SwipeExtension}.
 */
public interface OnItemSwipedListener {

    /**
     * Notified from {@link smartadapter.widget.DragAndDropExtension#onSwiped(RecyclerView.ViewHolder, int)}
     * with target view holder and swipe direction.
     * @param viewHolder target view holder
     * @param direction direction the item was swiped
     */
    void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction);
}
