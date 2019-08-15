package smartadapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import smartadapter.internal.factory.SmartRecyclerAdapterExtensionFactory;
import smartadapter.internal.mapper.ViewEventMapper;
import smartadapter.listener.OnItemSelectedListener;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Builder for SmartRecyclerAdapter.
 */
public class SmartAdapterBuilder {

    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<String, Class<? extends SmartViewHolder>> viewHolderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, SmartRecyclerAdapter> smartRecyclerAdapterMapper = new HashMap<>();
    private ViewEventMapper viewEventMapper = new ViewEventMapper();
    private SmartRecyclerAdapterExtensionFactory smartRecyclerAdapterExtensionFactory = new SmartRecyclerAdapterExtensionFactory();

    SmartAdapterBuilder(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
    }

    public final SmartAdapterBuilder map(@NonNull Class<?> itemType, @NonNull Class<? extends SmartViewHolder> viewHolderType) {
        viewHolderMapper.put(itemType.getName(), viewHolderType);
        return this;
    }

    public final SmartAdapterBuilder map(@NonNull Class<? extends SmartViewHolder> viewHolderType, @NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        smartRecyclerAdapterMapper.put(viewHolderType, smartRecyclerAdapter);
        return this;
    }

    public final SmartAdapterBuilder setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    private RecyclerView.LayoutManager getLayoutManager(Context context) {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(context);
        }
        return layoutManager;
    }

    public final SmartAdapterBuilder setViewTypeResolver(@NonNull ViewTypeResolver viewTypeResolver) {
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
    public final SmartAdapterBuilder addViewEventListener(@NonNull OnViewEventListener viewEventListener) {
        if (viewEventListener instanceof OnItemSelectedListener) {
            ((OnItemSelectedListener)viewEventListener).getSelectionStateHolder().setSmartRecyclerAdapter(smartRecyclerAdapter);
            viewEventMapper.addViewEventListener(viewEventListener);
        } else {
            viewEventMapper.addViewEventListener(viewEventListener);
        }
        return this;
    }

    /**
     * Adds {@link SmartRecyclerAdapterExtensionBuilder} to {@link SmartRecyclerAdapterExtensionFactory} that will build and bind
     * the {@link SmartRecyclerAdapter} and {@link RecyclerView} to the ExtensionBuilder.
     *
     * @param smartExtensionBuilder extension builder
     * @return SmartAdapterBuilder
     */
    public final SmartAdapterBuilder addExtensionBuilder(@NonNull SmartRecyclerAdapterExtensionBuilder smartExtensionBuilder) {
        smartRecyclerAdapterExtensionFactory.add(smartExtensionBuilder);
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
        smartRecyclerAdapterExtensionFactory.build(smartRecyclerAdapter, recyclerView);
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
