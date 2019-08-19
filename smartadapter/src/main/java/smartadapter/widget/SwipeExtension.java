package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.List;

import smartadapter.listener.OnItemSwipedListener;
import smartadapter.viewholder.SmartViewHolder;

public abstract class SwipeExtension extends ItemTouchHelper.Callback {

    abstract public void setSwipeFlags(int swipeFlags);

    abstract public void setLongPressDragEnabled(boolean longPressDragEnabled);

    abstract public void setViewHolderTypes(@NonNull List<Class<? extends SmartViewHolder>> viewHolderTypes);

    abstract void setOnItemSwipedListener(@NonNull OnItemSwipedListener onItemSwipedListener);
}
