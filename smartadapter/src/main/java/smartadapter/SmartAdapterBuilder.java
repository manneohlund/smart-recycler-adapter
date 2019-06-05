package smartadapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public class SmartAdapterBuilder {

    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> mapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListenerMap = new HashMap<>();
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
     *
     * @param viewEventListener global ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(ViewEventListener viewEventListener) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, R.id.undefined, viewEventListener);
    }

    public final SmartAdapterBuilder addViewEventListener(ViewEventListener viewEventListener, int actionId) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, actionId, viewEventListener);
    }

    /**
     * Will add ViewEventListener to all ViewHolders and assign simple list item onClickListener and/or onLongClickListener event listener.
     *
     * @param viewEventListener global ViewEventListener
     * @param viewEvent view event id, for predefined see smartadapter.R.id.xxx
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(int viewEvent, ViewEventListener viewEventListener) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, viewEvent, viewEventListener);
    }

    /**
     * Will automatically add a onClick viewEventListener to the viewHolderTypes view.
     *
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, R.id.undefined, viewEventListener);
    }

    /**
     *
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewEvent view event id, for predefined see smartadapter.R.id.xxx
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, int viewEvent, ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, viewEvent, viewEventListener);
    }

    /**
     * Specify the target view and automatically add a viewEventListener to the viewHolderTypes view.
     * @param viewHolderType ViewHolder that extends SmartViewHolder
     * @param viewId target view id for the ViewHolder type
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(Class<? extends SmartViewHolder> viewHolderType, int viewId, int viewEvent, ViewEventListener viewEventListener) {
        HashMap<Integer, HashMap<Integer, ViewEventListener>> mapper;
        if ((mapper = viewEventListenerMap.get(viewHolderType)) == null) {
            mapper = new HashMap<>();
        }
        if (!mapper.containsKey(viewId)) {
            final HashMap<Integer, ViewEventListener> viewEventAndListenerMap = new HashMap<>();
            viewEventAndListenerMap.put(viewEvent, viewEventListener);
            mapper.put(viewId, viewEventAndListenerMap);
        }
        mapper.get(viewId).put(viewEvent, viewEventListener);
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
