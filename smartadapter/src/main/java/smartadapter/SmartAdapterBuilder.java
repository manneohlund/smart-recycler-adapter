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

import smartadapter.listener.OnViewActionListener;
import smartadapter.viewholder.SmartAutoEventViewHolder;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

import static smartadapter.internal.utils.ViewEventValidator.isViewEventIdValid;

/**
 * Builder for SmartRecyclerAdapter.
 */
public class SmartAdapterBuilder {

    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> viewHolderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, SmartRecyclerAdapter> smartRecyclerAdapterMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, OnViewActionListener>>> viewActionListenerMap = new HashMap<>();
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
     * Adds {@link OnViewActionListener} to the {@link SmartRecyclerAdapter}.
     * The adapter will then automatically map the {@link OnViewActionListener} to the target view holder class with {@link OnViewActionListener#getViewHolderType()},
     * set the viewActionListener on the right View with viewId using {@link OnViewActionListener#getViewId()}.
     *
     * @see OnViewActionListener#getViewActionId() for predefined actionIds.
     *
     * @param viewActionListener target OnViewActionListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewActionListener(OnViewActionListener viewActionListener) {
        if (!isViewEventIdValid(viewActionListener.getViewActionId()))
            throw new RuntimeException(String.format("Invalid view event id (%d) for ViewHolder (%s)", viewActionListener.getViewActionId(), viewActionListener.getViewHolderType()));
        if (viewActionListener.getViewActionId() != R.id.undefined &&
                isViewEventIdValid(viewActionListener.getViewActionId()) &&
                !viewActionListener.getViewHolderType().equals(SmartViewHolder.class) &&
                !SmartAutoEventViewHolder.class.isAssignableFrom(viewActionListener.getViewHolderType()))
            throw new RuntimeException(String.format("View event id (%d) set but ViewHolder (%s) is not assignable from SmartAutoEventViewHolder",
                    viewActionListener.getViewActionId(),
                    viewActionListener.getViewHolderType()));

        HashMap<Integer, HashMap<Integer, OnViewActionListener>> mapper;
        if ((mapper = viewActionListenerMap.get(viewActionListener.getViewHolderType())) == null) {
            mapper = new HashMap<>();
        }
        if (!mapper.containsKey(viewActionListener.getViewId())) {
            final HashMap<Integer, OnViewActionListener> viewEventAndListenerMap = new HashMap<>();
            viewEventAndListenerMap.put(viewActionListener.getViewActionId(), viewActionListener);
            mapper.put(viewActionListener.getViewId(), viewEventAndListenerMap);
        }
        mapper.get(viewActionListener.getViewId()).put(viewActionListener.getViewActionId(), viewActionListener);
        this.viewActionListenerMap.put(viewActionListener.getViewHolderType(), mapper);
        return this;
    }

    @SuppressWarnings("unchecked")
    public final <T> T into(RecyclerView recyclerView) {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper);
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListeners(viewActionListenerMap);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView.getContext()));
        return (T) smartRecyclerAdapter;
    }

    @SuppressWarnings("unchecked")
    public final <T> T create() {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper);
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListeners(viewActionListenerMap);
        return (T) smartRecyclerAdapter;
    }
}
