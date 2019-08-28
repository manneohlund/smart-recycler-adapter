package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-17.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import smartadapter.SmartExtensionBuilder;
import smartadapter.SmartRecyclerAdapter;
import smartadapter.listener.OnItemMovedListener;
import smartadapter.viewholder.SmartAdapterHolder;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Builder for DragAndDropExtensions. Default constructor sets {@link BasicDragAndDropExtension}.
 */
public class DragAndDropExtensionBuilder implements SmartExtensionBuilder<DragAndDropExtension, DragAndDropExtensionBuilder> {

    private DragAndDropExtension dragAndDropExtension;
    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerView recyclerView;
    private int dragFlags;
    private boolean longPressDragEnabled;
    private List<Class<? extends SmartViewHolder>> viewHolderTypes = Collections.singletonList(SmartViewHolder.class);
    private OnItemMovedListener onItemMovedListener = (oldViewHolder, targetViewHolder) -> {}; // Noop

    public DragAndDropExtensionBuilder() {
        dragAndDropExtension = new BasicDragAndDropExtension();
    }

    public DragAndDropExtensionBuilder(DragAndDropExtension dragAndDropExtension) {
        this.dragAndDropExtension = dragAndDropExtension;
    }

    @Override
    public DragAndDropExtensionBuilder setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
        return this;
    }

    @Override
    public DragAndDropExtensionBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public DragAndDropExtensionBuilder setDragFlags(int dragFlags) {
        this.dragFlags = dragFlags;
        return this;
    }

    public DragAndDropExtensionBuilder setLongPressDragEnabled(boolean longPressDragEnabled) {
        this.longPressDragEnabled = longPressDragEnabled;
        return this;
    }

    @SafeVarargs
    public final DragAndDropExtensionBuilder setViewHolderTypes(@NonNull Class<? extends SmartViewHolder>... viewHolderTypes) {
        this.viewHolderTypes = Arrays.asList(viewHolderTypes);
        return this;
    }

    public DragAndDropExtensionBuilder setOnItemMovedListener(@NonNull OnItemMovedListener onItemMovedListener) {
        this.onItemMovedListener = onItemMovedListener;
        return this;
    }

    @Override
    public DragAndDropExtension build() {
        if (dragAndDropExtension instanceof SmartAdapterHolder) {
            ((SmartAdapterHolder) dragAndDropExtension).setSmartRecyclerAdapter(smartRecyclerAdapter);
        }
        dragAndDropExtension.setLongPressDragEnabled(longPressDragEnabled);
        dragAndDropExtension.setDragFlags(dragFlags);
        dragAndDropExtension.setViewHolderTypes(viewHolderTypes);
        dragAndDropExtension.setOnItemMovedListener(onItemMovedListener);
        ItemTouchHelper touchHelper = new ItemTouchHelper(dragAndDropExtension);
        dragAndDropExtension.setTouchHelper(touchHelper);
        touchHelper.attachToRecyclerView(recyclerView);
        dragAndDropExtension.setupDragAndDrop(recyclerView);
        return dragAndDropExtension;
    }
}