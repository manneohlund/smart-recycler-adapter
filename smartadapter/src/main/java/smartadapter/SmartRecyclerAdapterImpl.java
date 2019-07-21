package smartadapter;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2019 All rights reserved.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.internal.Mapper;
import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public class SmartRecyclerAdapterImpl extends RecyclerView.Adapter<SmartViewHolder> implements SmartRecyclerAdapter {

    private int itemCount = 0;
    private List items = new ArrayList();

    private final Mapper mapper;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListeners;
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;

    SmartRecyclerAdapterImpl(Object callerEnclosingClass, List items) {
        mapper = new Mapper(callerEnclosingClass);
        setItems(items, false);
        updateItemCount();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SmartViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (onViewDetachedFromWindowListener != null) {
            onViewDetachedFromWindowListener.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mapper.getItemViewType(viewTypeResolver, items.get(position), position);
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mapper.createViewHolder(viewEventListeners, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public <T> int getItemCount(Class<T> type) {
        int count = 0;
        for (Object item : items) {
            if (item.getClass().equals(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Object getItem(int index) {
        return items.get(index);
    }

    @Override
    public List getItems() {
        return items;
    }

    @Override
    public <T> ArrayList<T> getItems(Class<T> type) {
        ArrayList<T> itemOfType = new ArrayList<>();
        for (Object item : items) {
            if (item.getClass().equals(type)) {
                itemOfType.add((T) item);
            }
        }
        return itemOfType;
    }

    @Override
    public void setItems(List items) {
        setItems(items, true);
    }

    @Override
    public void setItems(List items, boolean notifyDataSetChanged) {
        if (items != null) {
            this.items = items;
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    @Override
    public void addItem(Object item) {
        this.addItem(item, true);
    }

    @Override
    public void addItem(Object item, boolean notifyDataSetChanged) {
        if (item != null) {
            this.items.add(item);
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    @Override
    public void addItem(int index, Object item) {
        addItem(index, item, true);
    }

    @Override
    public void addItem(int index, Object item, boolean notifyDataSetChanged) {
        if (item != null) {
            this.items.add(index, item);
            if (notifyDataSetChanged) {
                smartNotifyItemInserted(index);
            }
        }
    }

    @Override
    public void addItems(List items) {
        this.addItems(items, true);
    }

    @Override
    public void addItems(List items, boolean notifyDataSetChanged) {
        if (items != null) {
            this.items.addAll(items);
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    @Override
    public void addItems(int index, List items) {
        this.addItems(index, items, true);
    }

    @Override
    public void addItems(int index, List items, boolean notifyDataSetChanged) {
        if (items != null) {
            this.items.addAll(index, items);
            if (notifyDataSetChanged) {
                smartNotifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean removeItem(int index) {
        return this.removeItem(index, true);
    }

    @Override
    public boolean removeItem(int index, boolean notifyDataSetChanged) {
        if (items != null && !items.isEmpty()) {
            this.items.remove(index);
            if (notifyDataSetChanged) {
                smartNotifyItemRemoved(index);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean replaceItem(int index, Object item) {
        return replaceItem(index, item, true);
    }

    @Override
    public boolean replaceItem(int index, Object item, boolean notifyDataSetChanged) {
        if (item != null) {
            this.items.set(index, item);
            if (notifyDataSetChanged) {
                smartNotifyItemChanged(index);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.items.clear();
        smartNotifyDataSetChanged();
    }

    @Override
    public void smartNotifyDataSetChanged() {
        updateItemCount();
        notifyDataSetChanged();
    }

    @Override
    public void smartNotifyItemChanged(int position) {
        updateItemCount();
        notifyItemChanged(position);
    }

    @Override
    public void smartNotifyItemRangeChanged(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void smartNotifyItemInserted(int position) {
        updateItemCount();
        notifyItemInserted(position);
    }

    @Override
    public void smartNotifyItemRangeInserted(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void smartNotifyItemRemoved(int position) {
        updateItemCount();
        notifyItemRemoved(position);
    }

    @Override
    public void smartNotifyItemRangeRemoved(int positionStart, int itemCount) {
        updateItemCount();
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public final void updateItemCount() {
        itemCount = items.size();
    }

    @Override
    public void map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        mapper.addMapping(itemType, viewHolderType);
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    void setDataTypeViewHolderMapper(HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper) {
        mapper.setDataTypeViewHolderMapper(dataTypeViewHolderMapper);
    }

    void setSmartRecyclerAdapterMapper(HashMap<Class<? extends SmartViewHolder>, SmartRecyclerAdapter> smartRecyclerAdapterMapper) {
        mapper.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper);
    }

    @Override
    public ViewTypeResolver getViewTypeResolver() {
        return viewTypeResolver;
    }

    @Override
    public void setViewTypeResolver(ViewTypeResolver viewTypeResolver) {
        this.viewTypeResolver = viewTypeResolver;
    }

    @Override
    public HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> getViewEventListeners() {
        return this.viewEventListeners;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, ViewEventListener>> getViewEventListenersForViewHolder(Class<? extends SmartViewHolder> viewHolderType) {
        return getViewEventListeners().get(viewHolderType);
    }

    @Override
    public void setViewEventListeners(HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListeners) {
        this.viewEventListeners = viewEventListeners;
    }

    @Override
    public void setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
    }
}