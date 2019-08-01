package smartadapter;

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright © 2019 All rights reserved.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.internal.mapper.ViewHolderMapper;
import smartadapter.listener.OnViewActionListener;
import smartadapter.listener.OnViewAttachedToWindowListener;
import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.viewholder.RecyclableViewHolder;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * SmartRecyclerAdapter is the core implementation of the library.
 * It handles all the implementations of the {@link ISmartRecyclerAdapter} functionality.
 */
@SuppressWarnings("unchecked")
public class SmartRecyclerAdapter extends RecyclerView.Adapter<SmartViewHolder> implements ISmartRecyclerAdapter {

    private int itemCount = 0;
    private List items = new ArrayList();

    private final ViewHolderMapper mapper;
    private ViewTypeResolver viewTypeResolver;
    private HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, OnViewActionListener>>> viewEventListeners;

    SmartRecyclerAdapter(Object callerEnclosingClass, List items) {
        mapper = new ViewHolderMapper(callerEnclosingClass);
        setItems(items, false);
        updateItemCount();
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
    public void onViewRecycled(@NonNull SmartViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull SmartViewHolder holder) {
        if (holder instanceof RecyclableViewHolder) {
            return ((RecyclableViewHolder)holder).onFailedToRecycleView();
        }
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SmartViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof OnViewAttachedToWindowListener) {
            ((OnViewAttachedToWindowListener)holder).onViewAttachedToWindow();
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SmartViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof OnViewDetachedFromWindowListener) {
            ((OnViewDetachedFromWindowListener)holder).onViewDetachedFromWindow();
        }
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
                smartNotifyItemRangeInserted(getItemCount(), items.size());
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
                smartNotifyItemRangeInserted(index, items.size());
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
    public ViewHolderMapper getMapper() {
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
    public HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, OnViewActionListener>>> getViewEventListeners() {
        return this.viewEventListeners;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, OnViewActionListener>> getViewEventListenersForViewHolder(Class<? extends SmartViewHolder> viewHolderType) {
        return getViewEventListeners().get(viewHolderType);
    }

    @Override
    public void setViewEventListeners(HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, OnViewActionListener>>> viewEventListeners) {
        this.viewEventListeners = viewEventListeners;
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation.
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder items(List items) {
        return new SmartAdapterBuilder(new SmartRecyclerAdapter(null, items));
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation.
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder empty() {
        return new SmartAdapterBuilder(new SmartRecyclerAdapter(null, new ArrayList()));
    }
}