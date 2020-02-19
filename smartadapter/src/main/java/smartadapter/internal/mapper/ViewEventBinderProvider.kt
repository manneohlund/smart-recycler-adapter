package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 2019-08-02.
 * Copyright (c) All rights reserved.
 */

import android.util.SparseArray
import android.view.View
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewEventId
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnViewEventListener
import smartadapter.state.SmartStateHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.StatefulViewHolder

/**
 * Provides the view listener binding logic for views.
 */
class ViewEventBinderProvider internal constructor() {

    private val eventBinders = SparseArray<ViewEventBinder<*>>()

    init {
        add(OnItemClickListener::class.hashCode(), OnItemClickListenerBinder())
        add(OnItemLongClickListener::class.hashCode(), OnItemLongClickListenerBinder())
        add(OnItemSelectedListener::class.hashCode(), OnItemSelectedListenerBinder())
    }

    fun <T> add(listenerId: Int, listenerBinder: T)
        where T : ViewEventBinder<*> {
        eventBinders.append(listenerId, listenerBinder)
    }

    fun resolveViewEventId(viewEventListener: OnViewEventListener<*>): Int {
        return when (viewEventListener) {
            is OnItemSelectedListener -> OnItemSelectedListener::class.hashCode()
            is OnItemClickListener -> OnItemClickListener::class.hashCode()
            is OnItemLongClickListener -> OnItemLongClickListener::class.hashCode()
            else -> viewEventListener::class.hashCode()
        }
    }

    fun bind(
        smartRecyclerAdapter: SmartRecyclerAdapter,
        smartViewHolder: SmartViewHolder<*>,
        targetView: View,
        viewEventListener: OnViewEventListener<*>,
        viewEventId: ViewEventId
    ) {
        if (eventBinders.indexOfKey(viewEventId) >= 0)
            (eventBinders.get(viewEventId) as ViewEventBinder<OnViewEventListener<*>>)
                .bindListener(smartRecyclerAdapter, smartViewHolder, targetView, viewEventListener)
    }

    /*
     * Binder definitions
     */

    internal interface ViewEventBinder<T : OnViewEventListener<*>> {
        fun bindListener(
            smartRecyclerAdapter: SmartRecyclerAdapter,
            smartViewHolder: SmartViewHolder<*>,
            targetView: View,
            viewEventListener: T
        )
    }

    internal class OnItemClickListenerBinder : ViewEventBinder<OnItemClickListener> {
        override fun bindListener(
            smartRecyclerAdapter: SmartRecyclerAdapter,
            smartViewHolder: SmartViewHolder<*>,
            targetView: View,
            viewEventListener: OnItemClickListener
        ) {
            targetView.setOnClickListener { v ->
                viewEventListener.listener.invoke(v, smartRecyclerAdapter, smartViewHolder.adapterPosition)
            }
        }
    }

    internal class OnItemLongClickListenerBinder : ViewEventBinder<OnItemLongClickListener> {
        override fun bindListener(
            smartRecyclerAdapter: SmartRecyclerAdapter,
            smartViewHolder: SmartViewHolder<*>,
            targetView: View,
            viewEventListener: OnItemLongClickListener
        ) {
            targetView.setOnLongClickListener { v ->
                viewEventListener.listener.invoke(v, smartRecyclerAdapter, smartViewHolder.adapterPosition)
                true
            }
        }
    }

    // TODO Fix state holder
    internal class OnItemSelectedListenerBinder : ViewEventBinder<OnItemSelectedListener> {
        @Suppress("UNCHECKED_CAST")
        override fun bindListener(
            smartRecyclerAdapter: SmartRecyclerAdapter,
            smartViewHolder: SmartViewHolder<*>,
            targetView: View,
            viewEventListener: OnItemSelectedListener
        ) {

            if (smartViewHolder is StatefulViewHolder<*> && viewEventListener is OnItemSelectedListener) {
                (smartViewHolder as? StatefulViewHolder<SmartStateHolder>)?.stateHolder = viewEventListener.selectionStateHolder
            }

            if (viewEventListener.enableOnLongClick) {
                targetView.setOnLongClickListener { view ->
                    viewEventListener
                        .selectionStateHolder
                        .toggle(smartViewHolder.adapterPosition)
                    viewEventListener.listener.invoke(view, smartRecyclerAdapter, smartViewHolder.adapterPosition)
                    true
                }
            }

            targetView.setOnClickListener { view ->
                if (!viewEventListener.enableOnLongClick
                    || viewEventListener.enableOnLongClick
                    && viewEventListener.selectionStateHolder.selectedItemsCount > 0
                ) {
                    viewEventListener
                        .selectionStateHolder
                        .toggle(smartViewHolder.adapterPosition)
                }
                viewEventListener.listener.invoke(view, smartRecyclerAdapter, smartViewHolder.adapterPosition)
            }
        }
    }
}
