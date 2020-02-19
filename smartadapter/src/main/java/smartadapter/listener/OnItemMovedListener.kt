package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView

/**
 * Type alias lambda callback listener for drag and drop callbacks from extensions of [smartadapter.widget.DragAndDropExtension].
 */
typealias OnItemMovedListener = (
    oldViewHolder: RecyclerView.ViewHolder,
    targetViewHolder: RecyclerView.ViewHolder
) -> Unit
