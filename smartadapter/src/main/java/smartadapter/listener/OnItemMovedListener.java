package smartadapter.listener;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Listener for drag and drop callbacks from extensions of {@link smartadapter.widget.DragAndDropExtension}.
 */
public interface OnItemMovedListener {

    /**
     * Notified from {@link smartadapter.widget.DragAndDropExtension#onMove(RecyclerView, RecyclerView.ViewHolder, RecyclerView.ViewHolder)}
     * with old and new view holder that should switch places.
     * @param oldViewHolder from view holder
     * @param targetViewHolder to view holder
     */
    void onMoved(@NonNull RecyclerView.ViewHolder oldViewHolder, @NonNull RecyclerView.ViewHolder targetViewHolder);
}
