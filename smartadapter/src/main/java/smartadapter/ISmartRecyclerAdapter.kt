package smartadapter

/*
 * Created by Manne Öhlund on 2019-06-25.
 * Copyright (c) All rights reserved.
 */

import android.util.SparseArray
import androidx.recyclerview.widget.RecyclerView
import smartadapter.internal.mapper.ViewEventMapper
import smartadapter.internal.mapper.ViewHolderMapper
import smartadapter.listener.OnViewAttachedToWindowListener
import smartadapter.listener.OnViewDetachedFromWindowListener
import smartadapter.listener.OnViewEventListener
import smartadapter.widget.ViewTypeResolver
import kotlin.reflect.KClass

/**
 * Basic definitions of a [SmartRecyclerAdapter] implementation.
 */
interface ISmartRecyclerAdapter {

    /**
     * Holder of real item count. Used for async adding items without disrupting adapter state.
     * @see RecyclerView.Adapter.getItemCount
     */
    var smartItemCount: Int

    /**
     * Holder of all data to view holder mapping.
     * @see ViewHolderMapper
     */
    var viewHolderMapper: ViewHolderMapper

    /**
     * Holder of all view event listeners.
     * @see OnViewEventListener]
     */
    var viewEventMapper: ViewEventMapper

    /**
     * Sets custom view type resolver. Used when basic class mapping is not possible.
     * @see ViewTypeResolver].
     */
    var viewTypeResolver: ViewTypeResolver?

    /**
     * Get item count for target class type.
     * @see RecyclerView.Adapter.getItemCount
     * @param type target class type
     * @param <T> type of class
     * @return item count
     */
    fun <T : Any> getItemCount(type: KClass<out T>): Int

    /**
     * Get item at index.
     * @param index adapter index
     * @return Data object for that index.
     */
    fun getItem(index: Int): Any

    /**
     * Get list of all data items.
     * @return list of all data items
     */
    fun getItems(): MutableList<Any>

    /**
     * Get list of all data items for target class type.
     * @param type target class type
     * @param <T> type of class
     * @return list of all data items for target class type
    */
    fun <T : Any> getItems(type: KClass<out T>): MutableList<T>

    /**
     * Sets the data item list for the SmartRecyclerAdapter.
     * Calls [.setItems] with default notifyDataSetChanged to true.
     * @param items list of data items
     */
    fun setItems(items: MutableList<*>)

    /**
     * Sets the data item list for the SmartRecyclerAdapter and notifies the RecyclerView to update.
     * @param items list of data items
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    fun setItems(items: MutableList<*>, notifyDataSetChanged: Boolean)

    /**
     * Adds item to the list of data.
     * Calls [.addItem] with default notifyDataSetChanged to true.
     * @param item any type of item
     */
    fun addItem(item: Any)

    /**
     * Adds item to the list of data and notifies the RecyclerView to update.
     * @param item any type of item
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    fun addItem(item: Any, notifyDataSetChanged: Boolean)

    /**
     * Adds item to the list of data at target index.
     * Calls [.addItem] with default notifyDataSetChanged to true.
     * @param index target index
     * @param item any type of item
     */
    fun addItem(index: Int, item: Any)

    /**
     * Adds item to the list of data at target index and notifies the RecyclerView to update.
     * @param index target index
     * @param item any type of item
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    fun addItem(index: Int, item: Any, notifyDataSetChanged: Boolean)

    /**
     * Adds a list of items to the SmartRecyclerAdapter list of data.
     * Calls [.addItems] with default notifyDataSetChanged to true.
     * @param items list of items to add
     */
    fun addItems(items: List<Any>)

    /**
     * Adds a list of items to the SmartRecyclerAdapter list of data and notifies the RecyclerView to update.
     * @param items list of items to add
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    fun addItems(items: List<Any>, notifyDataSetChanged: Boolean)

    /**
     * Adds a list of items from index to the SmartRecyclerAdapter list of data.
     * Calls [.addItems] with default notifyDataSetChanged to true.
     * @param index target index
     * @param items list of items to add
     */
    fun addItems(index: Int, items: List<Any>)

    /**
     * Adds a list of items from index to the SmartRecyclerAdapter list of data and notifies the RecyclerView to update.
     * @param index target index
     * @param items list of items to add
     * @param notifyDataSetChanged indicates if RecyclerView should update.
     */
    fun addItems(index: Int, items: List<Any>, notifyDataSetChanged: Boolean)

    /**
     * Removes item at index.
     * @see .removeItem
     * @param index item index
     * @return true if item was removed
     */
    fun removeItem(index: Int): Boolean

    /**
     * Removes item at index.
     * @param index item index
     * @param notifyDataSetChanged updates recycler view with the new data
     * @return true if item was removed
     */
    fun removeItem(index: Int, notifyDataSetChanged: Boolean): Boolean

    /**
     * Replaces item at index.
     * @see .replaceItem
     * @param index item index
     * @return true if item was replaced
     */
    fun replaceItem(index: Int, item: Any)

    /**
     * Replaces item at index.
     * @param index item index
     * @param notifyDataSetChanged updates recycler view with the new data
     * @return true if item was replaced
     */
    fun replaceItem(index: Int, item: Any, notifyDataSetChanged: Boolean)

    /**
     * Clears all the data and calls [.smartNotifyDataSetChanged]
     */
    fun clear()

    /**
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyDataSetChanged]
     */
    fun smartNotifyDataSetChanged()

    /**
     * Notifies the recycler adapter that item at position has changed.
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyItemChanged]
     * @param position adapter position.
     */
    fun smartNotifyItemChanged(position: Position)

    /**
     * Notifies the recycler adapter that item range at position has changed.
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyItemRangeChanged]
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    fun smartNotifyItemRangeChanged(positionStart: Int, itemCount: Int)

    /**
     * Notifies the recycler adapter that item at position has been inserted.
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyItemInserted]
     * @param position item inserted at this position
     */
    fun smartNotifyItemInserted(position: Position)

    /**
     * Notifies the recycler adapter that item range from position has changed.
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyItemRangeInserted]
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    fun smartNotifyItemRangeInserted(positionStart: Int, itemCount: Int)

    /**
     * Notifies the recycler adapter that item at position has been removed.
     * @param position item removed at this position
     */
    fun smartNotifyItemRemoved(position: Position)

    /**
     * Notifies the recycler adapter that item range from position has been removed.
     * Calls [.updateItemCount] and [RecyclerView.Adapter.notifyItemRangeRemoved]
     * @param positionStart from position
     * @param itemCount item count from positionStart
     */
    fun smartNotifyItemRangeRemoved(positionStart: Int, itemCount: Int)

    /**
     * Updated the SmartRecyclerAdapter item count.
     */
    fun updateItemCount()

    /**
     * Maps data item type with SmartViewHolder extension.
     * @param itemType data item type
     * @param viewHolderType view holder type
     */
    fun map(itemType: KClass<*>, viewHolderType: SmartViewHolderType)

    /**
     * Get ViewEventListeners for target ViewHolder.
     * @param viewHolderType SmartViewHolder type
     * @return map of ViewEventListeners
     */
    fun getViewEventListenersForViewHolder(viewHolderType: SmartViewHolderType): SparseArray<SparseArray<OnViewEventListener<*>>>?

    /**
     * Adds an [OnViewAttachedToWindowListener] to the [SmartRecyclerAdapter] that will be called from [SmartRecyclerAdapter.onViewAttachedToWindow].
     * @param onViewAttachedToWindowListener listener
     */
    fun addOnViewAttachedToWindowListener(onViewAttachedToWindowListener: OnViewAttachedToWindowListener)

    /**
     * Adds an [smartadapter.listener.OnViewDetachedFromWindowListener] to the [SmartRecyclerAdapter] that will be called from [SmartRecyclerAdapter.onViewDetachedFromWindow].
     * @param onViewDetachedFromWindowListener listener
     */
    fun addOnViewDetachedFromWindowListener(onViewDetachedFromWindowListener: OnViewDetachedFromWindowListener)
}
