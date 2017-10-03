package smartadapter;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.manager.Mapper;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public class SmartRecyclerAdapter<C extends SmartViewHolder> extends RecyclerView.Adapter<C> {


    private int itemCount = 0;
    private ArrayList items = new ArrayList();

    private final Mapper mapper;
    private ViewTypeResolver viewTypeResolver;
    private ViewEventListener viewEventListener;
    private OnViewDetachedFromWindowListener onViewDetachedFromWindowListener;

    public SmartRecyclerAdapter(Object callerEnclosingClass, List items) {
        mapper = new Mapper(callerEnclosingClass);
        setItems(items);
    }

    @Override
    public void onViewDetachedFromWindow(C holder) {
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
    public C onCreateViewHolder(ViewGroup parent, int viewType) {
        return mapper.getViewHolder(viewEventListener, parent, viewType);
    }

    @Override
    public void onBindViewHolder(C holder, int position) {
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
        notifyAdapterDataSetChanged();
    }

    public void addItem(Object item) {
        this.addItem(item, true);
    }

    public void addItem(Object item, boolean notifyDataSetChanged) {
        if (item != null) {
            this.items.add(item);
            if (notifyDataSetChanged) {
                notifyAdapterDataSetChanged();
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
                notifyAdapterDataSetChanged();
            }
        }
    }

    public void removeItem(int index) {
        this.removeItem(index, true);
    }

    public void removeItem(int index, boolean notifyDataSetChanged) {
        this.items.remove(index);
        if (notifyDataSetChanged) {
            notifyAdapterDataSetChanged();
        }
    }

    public void clear() {
        this.items.clear();
        notifyAdapterDataSetChanged();
    }


    public void notifyAdapterDataSetChanged() {
        itemCount = items.size();
        notifyDataSetChanged();
    }

    public void map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        mapper.addMapping(itemType, viewHolderType);
    }

    protected void setMapper(HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper) {
        mapper.setDataTypeViewHolderMapper(dataTypeViewHolderMapper);
    }

    public void setViewTypeResolver(ViewTypeResolver viewTypeResolver) {
        this.viewTypeResolver = viewTypeResolver;
    }

    public void setViewEventListener(ViewEventListener viewEventListener) {
        this.viewEventListener = viewEventListener;
    }

    public void setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener) {
        this.onViewDetachedFromWindowListener = onViewDetachedFromWindowListener;
    }

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation
     * @return SmartAdapterBuilder
     */
    public static SmartAdapterBuilder init(@NonNull Activity caller) {
        return new SmartAdapterBuilder(caller, caller);
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static SmartAdapterBuilder init(@NonNull Fragment caller) {
        return new SmartAdapterBuilder(caller, caller.getActivity());
    }
    public static SmartAdapterBuilder init(@NonNull android.support.v4.app.Fragment caller) {
        return new SmartAdapterBuilder(caller, ((android.support.v4.app.Fragment) caller).getActivity());
    }
}