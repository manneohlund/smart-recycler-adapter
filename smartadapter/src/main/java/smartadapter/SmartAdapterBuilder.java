package smartadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import java.util.HashMap;
import java.util.List;

import smartadapter.datatype.ViewEvent;
import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2017 All rights reserved.
 */

public class SmartAdapterBuilder {

    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> mapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, Pair<Integer, ViewEventListener>>> viewEventListenerMap = new HashMap<>();
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;
    private List items;

    SmartAdapterBuilder(List items) {
        this.items = items;
    }

    public final SmartAdapterBuilder map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        mapper.put(itemType.getName(), viewHolderType);
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
     * Will add ViewEventListener to all ViewHolders and assign simple list item onClickListener event listener.
     * @see ViewEvent
     *
     * @param viewEventListener global ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(ViewEventListener viewEventListener) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, ViewEvent.ON_CLICK, viewEventListener);
    }

    /**
     * Will add ViewEventListener to all ViewHolders and assign simple list item onClickListener and/or onLongClickListener event listener.
     * @see ViewEvent
     *
     * @param viewEventListener global ViewEventListener
     * @param viewEvents ViewEvent.ON_CLICK | ViewEvent.ON_LONG_CLICK
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(int viewEvents, ViewEventListener viewEventListener) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, viewEvents, viewEventListener);
    }

    /**
     * Will automatically add a viewEventListener to the viewHolderTypes view.
     *
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, ViewEvent.ON_CLICK, viewEventListener);
    }

    /**
     *
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewEvents ViewEvent.ON_CLICK | ViewEvent.ON_LONG_CLICK
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, int viewEvents, ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, viewEvents, viewEventListener);
    }

    /**
     * Specify the target view and automatically add a viewEventListener to the viewHolderTypes view.
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewId target view id for the ViewHolder type
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, int viewId, int viewEvents, ViewEventListener viewEventListener) {
        HashMap<Integer, Pair<Integer, ViewEventListener>> mapper;
        if ((mapper = viewEventListenerMap.get(viewHolderType)) == null) {
            mapper = new HashMap<>();
        }
        mapper.put(viewId, new Pair<>(viewEvents, viewEventListener));
        this.viewEventListenerMap.put(viewHolderType, mapper);
        return this;
    }

    public final SmartAdapterBuilder setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
        return this;
    }

    public final SmartRecyclerAdapter into(RecyclerView recyclerView) {
        SmartRecyclerAdapter smartRecyclerAdapter = new SmartRecyclerAdapter(recyclerView.getContext(), items);
        smartRecyclerAdapter.setMapper(mapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListeners(viewEventListenerMap);
        smartRecyclerAdapter.setOnViewDetachedFromWindowListener(onViewDetachedFromWindowListener);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView.getContext()));
        return smartRecyclerAdapter;
    }
}
