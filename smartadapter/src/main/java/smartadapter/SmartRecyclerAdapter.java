package smartadapter;

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartadapter.listener.OnViewDetachedFromWindowListener;
import smartadapter.listener.ViewEventListener;
import smartadapter.manager.Mapper;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public interface SmartRecyclerAdapter {

    /**
     * Overrides {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}.
     * @see RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)
     */
    void onViewDetachedFromWindow(@NonNull SmartViewHolder holder);

    /**
     * Overrides {@link RecyclerView.Adapter#getItemCount()}.
     * @see RecyclerView.Adapter#getItemCount()
     * @return data item count
     */
    int getItemCount();

    /**
     * Get item count for target class type.
     * @param type target class type
     * @param <T> type of class
     * @return item count
     */
    <T> int getItemCount(Class<T> type);

    /**
     * Get item at index.
     * @param index adapter index
     * @return Data object for that index.
     */
    Object getItem(int index);

    /**
     * Get list of all data items.
     * @return list of all data items
     */
    List getItems();

    /**
     * Get list of all data items for target class type.
     * @param type target class type
     * @param <T> type of class
     * @return list of all data items for target class type
     */
    <T> ArrayList<T> getItems(Class<T> type);

    /**
     * Sets the data item list for the SmartRecyclerAdapter.
     * @param items list of data items
     */
    void setItems(List items);

    /**
     * Sets the data item list for the SmartRecyclerAdapter and notifies the RecyclerView to update.
     * @param items list of data items
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    void setItems(List items, boolean notifyDataSetChanged);

    /**
     * Adds item to the list of data.
     * @param item any type of item
     */
    void addItem(Object item);

    /**
     * Adds item to the list of data and notifies the RecyclerView to update.
     * @param item any type of item
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    void addItem(Object item, boolean notifyDataSetChanged);

    /**
     * Adds a list of items to the SmartRecyclerAdapter list of data.
     * @param items list of items to add
     */
    void addItems(List items);

    /**
     * Adds a list of items to the SmartRecyclerAdapter list of data and notifies the RecyclerView to update.
     * @param items list of items to add
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    void addItems(List items, boolean notifyDataSetChanged);

    /**
     * Removes item at index.
     * @see #removeItem(int, boolean)
     *
     * @param index item index
     * @return true if item was removed
     */
    boolean removeItem(int index);

    /**
     * Removes item at index.
     * @param index item index
     * @param notifyDataSetChanged updates recycler view with the new data
     * @return true if item was removed
     */
    boolean removeItem(int index, boolean notifyDataSetChanged);

    /**
     * Replaces item at index.
     * @see #replaceItem(int, Object, boolean)
     *
     * @param index item index
     * @return true if item was replaced
     */
    boolean replaceItem(int index, Object item);

    /**
     * Replaces item at index.
     * @param index item index
     * @param notifyDataSetChanged updates recycler view with the new data
     * @return true if item was replaced
     */
    boolean replaceItem(int index, Object item, boolean notifyDataSetChanged);

    /**
     * Clears all the data and calls {@link #smartNotifyDataSetChanged()}
     */
    void clear();

    /**
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyDataSetChanged()}
     */
    void smartNotifyDataSetChanged();

    /**
     * Notifies the recycler adapter that item at position has changed.
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyItemChanged(int)}
     * @param position adapter position.
     */
    void smartNotifyItemChanged(int position);

    /**
     * Notifies the recycler adapter that item range at position has changed.
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyItemRangeChanged(int, int)}
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    void smartNotifyItemRangeChanged(int positionStart, int itemCount);

    /**
     * Notifies the recycler adapter that item at position has been inserted.
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyItemInserted(int)}
     * @param position item inserted at this position
     */
    void smartNotifyItemInserted(int position);

    /**
     * Notifies the recycler adapter that item range from position has changed.
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyItemRangeInserted(int, int)}
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    void smartNotifyItemRangeInserted(int positionStart, int itemCount);

    /**
     * Notifies the recycler adapter that item at position has been removed.
     * @param position item removed at this position
     */
    void smartNotifyItemRemoved(int position);

    /**
     * Notifies the recycler adapter that item range from position has been removed.
     * Calls {@link #updateItemCount()} and {@link RecyclerView.Adapter#notifyItemRangeRemoved(int, int)}
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    void smartNotifyItemRangeRemoved(int positionStart, int itemCount);

    /**
     * Updated the SmartAdapterItem count.
     */
    void updateItemCount();

    /**
     * Maps data item type with SmartViewHolder extension.
     * @param itemType data item type
     * @param viewHolderType view holder type
     */
    void map(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType);

    /**
     * Returns the data item view holder mapper.
     * @return Mapper
     */
    Mapper getMapper();

    /**
     * Returns {@link ViewTypeResolver}.
     * @return viewTypeResolver
     */
    ViewTypeResolver getViewTypeResolver();

    /**
     * Sets {@link ViewTypeResolver}.
     * @param viewTypeResolver the ViewTypeResolver
     */
    void setViewTypeResolver(ViewTypeResolver viewTypeResolver);

    /**
     * Get all ViewEventListeners.
     * @return map of ViewEventListeners
     */
    HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> getViewEventListeners();

    /**
     * Get ViewEventListeners for target ViewHolder.
     * @param viewHolderType SmartViewHolder type
     * @return map of ViewEventListeners
     */
    HashMap<Integer, HashMap<Integer, ViewEventListener>> getViewEventListenersForViewHolder(Class<? extends SmartViewHolder> viewHolderType);

    /**
     * Sets map of {@link ViewTypeResolver}.
     * @param viewEventListeners map of ViewEventListeners
     */
    void setViewEventListeners(HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListeners);

    /**
     * Sets OnViewDetachedFromWindowListener for view holder detach listening.
     * @param onViewDetachedFromWindowListener the OnViewDetachedFromWindowListener
     */
    void setOnViewDetachedFromWindowListener(OnViewDetachedFromWindowListener onViewDetachedFromWindowListener);

    /**
     * Builder of {@link SmartRecyclerAdapter} for easy implementation
     * @return SmartAdapterBuilder
     */
    static SmartAdapterBuilder items(List items) {
        return new SmartAdapterBuilder(items);
    }

    static SmartAdapterBuilder empty() {
        return new SmartAdapterBuilder(new ArrayList());
    }
}
