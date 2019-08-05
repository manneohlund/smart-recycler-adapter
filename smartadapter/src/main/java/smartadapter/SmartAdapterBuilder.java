package smartadapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

import smartadapter.internal.mapper.ViewEventMapper;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Builder for SmartRecyclerAdapter.
 */
public class SmartAdapterBuilder {

    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> viewHolderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, SmartRecyclerAdapter> smartRecyclerAdapterMapper = new HashMap<>();
    private ViewEventMapper viewEventMapper = new ViewEventMapper();
    private SmartRecyclerAdapter smartRecyclerAdapter;

    SmartAdapterBuilder(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    public final SmartAdapterBuilder map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        viewHolderMapper.put(itemType.getName(), viewHolderType);
        return this;
    }

    public final SmartAdapterBuilder map(Class<? extends SmartViewHolder> viewHolderType, SmartRecyclerAdapter smartRecyclerAdapter) {
        smartRecyclerAdapterMapper.put(viewHolderType, smartRecyclerAdapter);
        return this;
    }

    public final SmartAdapterBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    private RecyclerView.LayoutManager getLayoutManager(Context context) {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(context);
        }
        return layoutManager;
    }

    public final SmartAdapterBuilder setViewTypeResolver(ViewTypeResolver viewTypeResolver) {
        this.viewTypeResolver = viewTypeResolver;
        return this;
    }

    /**
     * Adds {@link OnViewEventListener} to the {@link SmartRecyclerAdapter}.
     * The adapter will then automatically map the {@link OnViewEventListener} to the target view holder class with {@link OnViewEventListener#getViewHolderType()},
     * set the viewActionListener on the right View with viewId using {@link OnViewEventListener#getViewId()}.
     *
     * @see OnViewEventListener#getViewEventId() for predefined eventIds.
     *
     * @param viewEventListener target {@link OnViewEventListener}
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(OnViewEventListener viewEventListener) {
        viewEventMapper.addViewEventListener(viewEventListener);
        return this;
    }

    @SuppressWarnings("unchecked")
    public final <T> T into(RecyclerView recyclerView) {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper);
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventMapper(viewEventMapper);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView.getContext()));
        return (T) smartRecyclerAdapter;
    }

    @SuppressWarnings("unchecked")
    public final <T> T create() {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper);
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventMapper(viewEventMapper);
        return (T) smartRecyclerAdapter;
    }
}
