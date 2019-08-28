package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-08-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.widget.BasicDragAndDropExtension;

/**
 * Provides target view for draggable purposes.
 * Lets ItemTouchHelper handler such as {@link BasicDragAndDropExtension}
 * to bind {@link androidx.recyclerview.widget.ItemTouchHelper#startDrag(RecyclerView.ViewHolder)}.
 */
public interface DraggableViewHolder {

    /**
     * Returns a target draggable view
     * @return view
     */
    @NonNull
    View getDraggableView();
}
