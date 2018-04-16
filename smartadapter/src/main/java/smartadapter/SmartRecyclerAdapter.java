package smartadapter;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.manager.Mapper;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public class SmartRecyclerAdapter extends RecyclerView.Adapter<SmartViewHolder> {

    private int itemCount = 0;
    private ArrayList items = new ArrayList();

    private final Mapper mapper;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, Pair<Integer, ViewEventListener>>> viewEventListeners;
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;

    SmartRecyclerAdapter(Object callerEnclosingClass, List items) {
        mapper = new Mapper(callerEnclosingClass);
        setItems(items);
    }

    @Override
    public void onViewDetachedFromWindow(SmartViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (onViewDetachedFromWindowListener != null) {
            onViewDetachedFromWindowListener.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mapper.getItemViewType(viewTypeResolver, items.get(position), position);
    }

    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mapper.getViewHolder(viewEventListeners, parent, viewType);
    }

    @Override
    public void onBindViewHolder(SmartViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public <T> int getItemCount(Class<T> type) {
        int count = 0;
        for (Object item : items) {
            if (item.getClass().equals(type)) {
                count++;
            }
        }
        return count;
    }

    public Object getItem(int index) {
        return items.get(index);
    }

    public ArrayList getItems() {
        return items;
    }

    public <T> ArrayList<T> getItems(Class<T> type) {
        ArrayList<T> itemOfType = new ArrayList<>();
        for (Object item : items) {
            if (item.getClass().equals(type)) {
                itemOfType.add((T) item);
            }
        }
        return itemOfType;
    }

    public void setItems(List items) {
        this.items.clear();
        this.items.addAll(items);
        smartNotifyDataSetChanged();
    }

    public void addItem(Object item) {
        this.addItem(item, true);
    }

    public void addItem(Object item, boolean notifyDataSetChanged) {
        if (item != null) {
            this.items.add(item);
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    public void addItems(List items) {
        this.addItems(items, true);
    }

    public void addItems(List items, boolean notifyDataSetChanged) {
        if (items != null) {
            this.items.addAll(items);
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    public void removeItem(int index) {
        this.removeItem(index, true);
    }

    public void removeItem(int index, boolean notifyDataSetChanged) {
        this.items.remove(index);
        if (notifyDataSetChanged) {
            smartNotifyItemRemoved(index);
        }
    }

    public void clear() {
        this.items.clear();
        smartNotifyDataSetChanged();
    }

    public void smartNotifyDataSetChanged() {
        updateItemCount();
        notifyDataSetChanged();
    }

    public void smartNotifyItemChanged(int position) {
        updateItemCount();
        notifyItemChanged(position);
    }

    public void smartNotifyItemRangeChanged(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeChanged(positionStart, itemCount);
    }

    public void smartNotifyItemInserted(int position) {
        updateItemCount();
        notifyItemInserted(position);
    }

    public void smartNotifyItemRangeInserted(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void smartNotifyItemRemoved(int position) {
        updateItemCount();
        notifyItemRemoved(position);
    }

    public void smartNotifyItemRangeRemoved(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    public final void updateItemCount() {
        itemCount = items.size();
    }

    public void map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        mapper.addMapping(itemType, viewHolderType);
    }

    public Mapper getMapper() {
        return mapper;
    }

    protected void setMapper(HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper) {
        mapper.setDataTypeViewHolderMapper(dataTypeViewHolderMapper);
    }

    public ViewTypeResolver getViewTypeResolver() {
        return viewTypeResolver;
    }

    public void setViewTypeResolver(ViewTypeResolver viewTypeResolver) {
        this.viewTypeResolver = viewTypeResolver;
    }

    public HashMap<Integer, Pair<Integer, ViewEventListener>> getViewEventListenersForViewHolder(Class<? extends SmartViewHolder> viewHolderType) {
        return getViewEventListeners().get(viewHolderType);
    }

    public void setViewEventListeners(HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, Pair<Integer, ViewEventListener>>> viewEventListeners) {
        this.viewEventListeners = viewEventListeners;
    }

    public HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, Pair<Integer, ViewEventListener>>> getViewEventListeners() {
        return this.viewEventListeners;
    }

    public void setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder items(List items) {
        return new SmartAdapterBuilder(items);
    }
    public static SmartAdapterBuilder empty() {
        return new SmartAdapterBuilder(new ArrayList());
    }
}