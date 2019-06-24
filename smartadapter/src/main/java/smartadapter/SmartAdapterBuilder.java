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
import smartadapter.viewholder.SmartAutoEventViewHolder;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

import static smartadapter.utils.ViewEventValidator.isViewEventIdValid;

public class SmartAdapterBuilder {

    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> viewHolderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, SmartAdapterBuilder> smartAdapterBuilderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListenerMap = new HashMap<>();
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;
    private List items;

    SmartAdapterBuilder(List items) {
        this.items = items;
    }

    public final SmartAdapterBuilder map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        viewHolderMapper.put(itemType.getName(), viewHolderType);
        return this;
    }

    public final SmartAdapterBuilder map(Class<? extends SmartViewHolder> viewHolderType, SmartAdapterBuilder smartAdapterBuilder) {
        smartAdapterBuilderMapper.put(viewHolderType, smartAdapterBuilder);
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

    public final SmartAdapterBuilder addViewEventListener(ViewEventListener viewEventListener, int autoViewEventId) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, autoViewEventId, viewEventListener);
    }

    /**
     * Will add ViewEventListener to all ViewHolders and assign simple list item onClickListener and/or onLongClickListener event listener.
     *
     * @param viewEventListener global ViewEventListener
     * @param autoViewEventId   target auto view event id, for predefined see smartadapter.R.id.xxx
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(
            int autoViewEventId,
            ViewEventListener viewEventListener) {
        return addViewEventListener(SmartViewHolder.class, R.id.undefined, autoViewEventId, viewEventListener);
    }

    /**
     * Will automatically add a onClick viewEventListener to the viewHolderTypes view.
     *
     * @param viewHolderType    ViewHolder that extends SmartViewHolder
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(
            Class<? extends SmartViewHolder> viewHolderType,
            ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, R.id.undefined, viewEventListener);
    }

    /**
     * @param viewHolderType    ViewHolder that extends SmartViewHolder
     * @param autoViewEventId   target auto view event id, for predefined see smartadapter.R.id.xxx
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(
            Class<? extends SmartViewHolder> viewHolderType,
            int autoViewEventId,
            ViewEventListener viewEventListener) {
        return addViewEventListener(viewHolderType, R.id.undefined, autoViewEventId, viewEventListener);
    }

    /**
     * Specify the target view and automatically add a viewEventListener to the viewHolderTypes view.
     *
     * @param viewHolderType    ViewHolder that extends SmartViewHolder
     * @param viewId            target view id for the ViewHolder type
     * @param autoViewEventId   target auto view event id, must be smartadapter.R.id
     * @param viewEventListener target ViewEventListener
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addViewEventListener(
            Class<? extends SmartViewHolder> viewHolderType,
            int viewId,
            int autoViewEventId,
            ViewEventListener viewEventListener) {
        if (!isViewEventIdValid(autoViewEventId))
            throw new RuntimeException(String.format("Invalid view event id (%d) for ViewHolder (%s)", autoViewEventId, viewHolderType));
        if (autoViewEventId != R.id.undefined &&
                isViewEventIdValid(autoViewEventId) &&
                !viewHolderType.equals(SmartViewHolder.class) &&
                !SmartAutoEventViewHolder.class.isAssignableFrom(viewHolderType))
            throw new RuntimeException(String.format("View event id (%d) set but ViewHolder (%s) is not assignable from SmartAutoEventViewHolder", autoViewEventId, viewHolderType));

        HashMap<Integer, HashMap<Integer, ViewEventListener>> mapper;
        if ((mapper = viewEventListenerMap.get(viewHolderType)) == null) {
            mapper = new HashMap<>();
        }
        if (!mapper.containsKey(viewId)) {
            final HashMap<Integer, ViewEventListener> viewEventAndListenerMap = new HashMap<>();
            viewEventAndListenerMap.put(autoViewEventId, viewEventListener);
            mapper.put(viewId, viewEventAndListenerMap);
        }
        mapper.get(viewId).put(autoViewEventId, viewEventListener);
        this.viewEventListenerMap.put(viewHolderType, mapper);
        return this;
    }

    public final SmartAdapterBuilder setOnViewDetachedFromWindowListener(
            OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
        return this;
    }

    public final SmartRecyclerAdapter into(RecyclerView recyclerView) {
        SmartRecyclerAdapterImpl smartRecyclerAdapter = new SmartRecyclerAdapterImpl(recyclerView.getContext(), items);
        smartRecyclerAdapter.setMapper(viewHolderMapper);
        smartRecyclerAdapter.setSmartAdapterBuilderMapper(smartAdapterBuilderMapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListeners(viewEventListenerMap);
        smartRecyclerAdapter.setOnViewDetachedFromWindowListener(onViewDetachedFromWindowListener);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView.getContext()));
        return smartRecyclerAdapter;
    }
}
