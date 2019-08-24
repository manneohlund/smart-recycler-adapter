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
import smartadapter.listener.OnItemSwipedListener;
import smartadapter.viewholder.SmartAdapterHolder;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Builder for {@link SwipeExtension} that is build from {@link smartadapter.internal.factory.SmartRecyclerAdapterExtensionFactory}.
 */
public class SwipeExtensionBuilder implements SmartExtensionBuilder<SwipeExtension, SwipeExtensionBuilder> {

    private SwipeExtension swipeExtension;
    private int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    private boolean longPressDragEnabled;
    private List<Class<? extends SmartViewHolder>> viewHolderTypes = Collections.singletonList(SmartViewHolder.class);
    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerView recyclerView;
    private OnItemSwipedListener onItemSwipedListener = (oldViewHolder, targetViewHolder) -> {}; // Noop

    public SwipeExtensionBuilder() {
        swipeExtension = new BasicSwipeExtension();
    }

    public SwipeExtensionBuilder(@NonNull SwipeExtension swipeExtension) {
        this.swipeExtension = swipeExtension;
    }

    @Override
    public SwipeExtensionBuilder setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
        return this;
    }

    @Override
    public SwipeExtensionBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public SwipeExtensionBuilder setOnItemSwipedListener(@NonNull OnItemSwipedListener onItemSwipedListener) {
        this.onItemSwipedListener = onItemSwipedListener;
        return this;
    }

    public SwipeExtensionBuilder setSwipeFlags(int swipeFlags) {
        this.swipeFlags = swipeFlags;
        return this;
    }

    public SwipeExtensionBuilder setLongPressDragEnabled(boolean longPressDragEnabled) {
        this.longPressDragEnabled = longPressDragEnabled;
        return this;
    }

    @SafeVarargs
    public final SwipeExtensionBuilder setViewHolderTypes(@NonNull Class<? extends SmartViewHolder>... viewHolderTypes) {
        this.viewHolderTypes = Arrays.asList(viewHolderTypes);
        return this;
    }

    @Override
    public SwipeExtension build() {
        if (swipeExtension instanceof SmartAdapterHolder) {
            ((SmartAdapterHolder) swipeExtension).setSmartRecyclerAdapter(smartRecyclerAdapter);
        }
        swipeExtension.setSwipeFlags(swipeFlags);
        swipeExtension.setLongPressDragEnabled(longPressDragEnabled);
        swipeExtension.setViewHolderTypes(viewHolderTypes);
        swipeExtension.setOnItemSwipedListener(onItemSwipedListener);
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeExtension);
        touchHelper.attachToRecyclerView(recyclerView);
        return swipeExtension;
    }
}
