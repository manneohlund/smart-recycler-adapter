package smartadapter.listeners

/*
 * Created by Manne Öhlund on 2020-09-23.
 * Copyright (c) All rights reserved.
 */

import android.graphics.Color
import android.widget.CompoundButton
import androidx.annotation.IdRes
import io.github.manneohlund.smartrecycleradapter.listeners.R
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.extension.setBackgroundAttribute
import smartadapter.listener.OnBindViewHolderListener
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.state.SmartStateHolder
import smartadapter.viewholder.SmartViewHolder
import java.util.TreeSet

/**
 * Extends [OnMultiItemSelectListener] and contains the logic for the single check states for recycler adapter positions.
 */
open class OnSingleItemCheckListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes viewId: ViewId = R.id.undefined
) : OnMultiItemCheckListener(viewId = viewId) {

    /**
     * Adds the position to the data set and [.disable]s any old positions.
     * @param position the adapter position
     */
    override fun enable(position: Position) {
        for (oldPositions in selectedItems) {
            disable(oldPositions)
        }
        clear()
        super.enable(position)
    }

    /**
     * Removes the position from the data set and calls [smartadapter.SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun disable(position: Position) {
        super.disable(position)
        smartRecyclerAdapter.smartNotifyItemChanged(position)
    }
}

/**
 * Extends [OnMultiItemSelectListener] and contains the logic for the single selection states for recycler adapter positions.
 */
open class OnSingleItemSelectListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes viewId: ViewId = R.id.undefined
) : OnMultiItemSelectListener(enableOnLongClick = false, viewId = viewId) {

    /**
     * Adds the position to the data set and [.disable]s any old positions.
     * @param position the adapter position
     */
    override fun enable(position: Position) {
        for (oldPositions in selectedItems) {
            disable(oldPositions)
        }
        clear()
        super.enable(position)
    }

    /**
     * Removes the position from the data set and calls [smartadapter.SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun disable(position: Position) {
        super.disable(position)
        smartRecyclerAdapter.smartNotifyItemChanged(position)
    }
}

/**
 * Extends [OnMultiItemSelectListener] and contains the logic for the multi check states for recycler adapter positions.
 */
open class OnMultiItemCheckListener(
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes viewId: ViewId = R.id.undefined
) : OnMultiItemSelectListener(enableOnLongClick = false, viewId = viewId) {

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        smartRecyclerAdapter = adapter

        val view = findView(viewId, viewHolder)

        with(view) {
            setOnClickListener {
                toggle(viewHolder.adapterPosition)
                eventListener.postValue(
                    ViewEvent.OnItemSelected(
                        adapter,
                        viewHolder.adapterPosition,
                        view,
                        isSelected(viewHolder.adapterPosition)
                    )
                )
            }
        }
    }

    override fun setSelected(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        val view = viewHolder.itemView.findViewById<CompoundButton>(viewId)
        view.isChecked = isSelected(viewHolder.adapterPosition)
    }
}

/**
 * Extends [OnViewEventListener] and contains the logic for the multi select states for recycler adapter positions.
 *
 * if [enableOnLongClick] is true multi select will be enabled after a long click.
 *
 * if [selectedItemsCount] is [R.id.undefined] and [enableOnLongClick] is true a regular [ViewEvent.OnClick] will be emitted.<br/>
 */
open class OnMultiItemSelectListener(
    val enableOnLongClick: Boolean = true,
    override val viewHolderType: SmartViewHolderType = SmartViewHolder::class,
    @IdRes val viewId: ViewId = R.id.undefined
) : OnViewEventListener<ViewEvent>(),
    SmartViewHolderBinder,
    SmartStateHolder,
    OnCreateViewHolderListener,
    OnBindViewHolderListener {

    /**
     * The target [SmartRecyclerAdapter].
     */
    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    /**
     * Provides sorted set of selected positions.
     */
    internal var selectedItems = TreeSet<Int>()

    /**
     * Provides selected item count.
     */
    val selectedItemsCount: Int
        get() = selectedItems.size

    override fun enable(position: Position) {
        selectedItems.add(position)
    }

    override fun disable(position: Position) {
        selectedItems.remove(position)
    }

    /**
     * Toggles selection of a position in adapter and calls [SmartRecyclerAdapter.smartNotifyItemChanged].
     * @param position the adapter position
     */
    override fun toggle(position: Position) {
        if (selectedItems.contains(position)) {
            disable(position)
        } else {
            enable(position)
        }
    }

    override fun clear() {
        selectedItems.clear()
    }

    /**
     * Checks if position is selected.
     * @param position position in adapter
     * @return true if position is contained in the selection set
     */
    fun isSelected(position: Position): Boolean {
        return selectedItems.contains(position)
    }

    /**
     * Removes selected items in the adapter with animation then clears the state holder set.
     */
    fun removeSelections() {
        for (position in selectedItems.descendingSet()) {
            smartRecyclerAdapter.removeItem(position)
        }
        selectedItems.clear()
    }

    override fun onCreateViewHolder(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        smartRecyclerAdapter = adapter

        val view = findView(viewId, viewHolder)

        with(view) {
            if (enableOnLongClick) {
                setOnLongClickListener {
                    toggle(viewHolder.adapterPosition)
                    setSelected(adapter, viewHolder)
                    smartRecyclerAdapter.smartNotifyItemChanged(viewHolder.adapterPosition)
                    eventListener.postValue(
                        ViewEvent.OnItemSelected(
                            adapter,
                            viewHolder.adapterPosition,
                            view,
                            isSelected(viewHolder.adapterPosition)
                        )
                    )
                    true
                }
            }
            setOnClickListener {
                if (!enableOnLongClick
                    || enableOnLongClick
                    && selectedItemsCount > 0
                ) {
                    toggle(viewHolder.adapterPosition)
                    setSelected(adapter, viewHolder)
                    smartRecyclerAdapter.smartNotifyItemChanged(viewHolder.adapterPosition)
                    eventListener.postValue(
                        ViewEvent.OnItemSelected(
                            adapter,
                            viewHolder.adapterPosition,
                            view,
                            isSelected(viewHolder.adapterPosition)
                        )
                    )
                } else {
                    eventListener.postValue(
                        ViewEvent.OnClick(adapter, viewHolder.adapterPosition, view)
                    )
                }
            }
        }
    }

    override fun onBindViewHolder(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {
        setSelected(adapter, viewHolder)
    }

    open fun setSelected(
        adapter: SmartRecyclerAdapter,
        viewHolder: SmartViewHolder<Any>
    ) {
        if (viewHolder is OnItemSelectListener) {
            viewHolder.onItemSelect(
                adapter,
                viewHolder.adapterPosition,
                viewHolder.itemView,
                isSelected(viewHolder.adapterPosition)
            )
        } else {
            if (isSelected(viewHolder.adapterPosition)) {
                viewHolder.itemView.setBackgroundColor(Color.RED)
            } else {
                viewHolder.itemView.setBackgroundAttribute(R.attr.selectableItemBackground)
            }
        }
    }
}
