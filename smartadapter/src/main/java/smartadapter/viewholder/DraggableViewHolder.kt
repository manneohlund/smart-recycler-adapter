package smartadapter.viewholder

/*
 * Created by Manne Öhlund on 2019-08-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import smartadapter.widget.BasicDragAndDropExtension

/**
 * Provides target view for draggable purposes.
 * Lets ItemTouchHelper handler such as [BasicDragAndDropExtension]
 * to bind [androidx.recyclerview.widget.ItemTouchHelper.startDrag].
 */
interface DraggableViewHolder {

    /**
     * Target draggable view
     */
    val draggableView: View
}
