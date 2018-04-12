package smartadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2017 All rights reserved.
 */

public class SmartAdapterBuilder {

    private Object caller;
    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> mapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, ViewEventListener>> viewEventListenerMap = new HashMap<>();
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;
    private List items = new ArrayList();

    SmartAdapterBuilder(Object caller, Context context) {
        this.caller = caller;
        layoutManager = new LinearLayoutManager(context);
    }

    public SmartAdapterBuilder map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        mapper.put(itemType.getName(), viewHolderType);
        return this;
    }

    public SmartAdapterBuilder items(List items) {
        this.items = items;
        return this;
    }

    public SmartAdapterBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public SmartAdapterBuilder setViewTypeResolver(ViewTypeResolver viewTypeResolver) {
        this.viewTypeResolver = viewTypeResolver;
        return this;
    }

    /**
     * Will automatically add a viewEventListener to the viewHolderTypes view.
     * @param viewHolderType
     * @param viewEventListener
     * @return SmartAdapterBuilder
     */
    public SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, viewEventListener);
    }

    /**
     * Specify the target view and automatically add a viewEventListener to the viewHolderTypes view.
     * @param viewHolderType
     * @param viewId
     * @param viewEventListener
     * @return SmartAdapterBuilder
     */
    public SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, int viewId, ViewEventListener viewEventListener) {
        HashMap<Integer, ViewEventListener> mapper;
        if ((mapper = viewEventListenerMap.get(viewHolderType)) == null) {
            mapper = new HashMap<>();
        }
        mapper.put(viewId, viewEventListener);
        this.viewEventListenerMap.put(viewHolderType, mapper);
        return this;
    }

    public SmartAdapterBuilder setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
        return this;
    }

    public SmartRecyclerAdapter into(RecyclerView recyclerView) {
        SmartRecyclerAdapter smartRecyclerAdapter = new SmartRecyclerAdapter(caller, items);
        smartRecyclerAdapter.setMapper(mapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListeners(viewEventListenerMap);
        smartRecyclerAdapter.setOnViewDetachedFromWindowListener(onViewDetachedFromWindowListener);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return smartRecyclerAdapter;
    }
}
