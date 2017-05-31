package smartadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private ViewEventListener viewEventListener;
    private HashMap<String, Class<? extends SmartViewHolder>> mapper = new HashMap<>();
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

    public SmartAdapterBuilder setViewEventListener(ViewEventListener viewEventListener) {
        this.viewEventListener = viewEventListener;
        return this;
    }

    public SmartRecyclerAdapter into(RecyclerView recyclerView) {
        SmartRecyclerAdapter smartRecyclerAdapter = new SmartRecyclerAdapter(caller, items);
        smartRecyclerAdapter.setMapper(mapper);
        smartRecyclerAdapter.setViewTypeResolver(viewTypeResolver);
        smartRecyclerAdapter.setViewEventListener(viewEventListener);
        recyclerView.setAdapter(smartRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return smartRecyclerAdapter;
    }
}
