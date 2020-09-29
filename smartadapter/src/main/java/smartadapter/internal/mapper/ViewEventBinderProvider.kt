package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 2019-08-02.
 * Copyright (c) All rights reserved.
 */

import android.util.SparseArray
import android.view.View
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.ViewEventId
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnViewEventListener
import smartadapter.state.SmartStateHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.StatefulViewHolder

/**
 * Provides the view listener binding logic for views.
 */
@Deprecated("Will be removed soon, use extension library 'io.github.manneohlund:smart-recycler-adapter-listeners'")
class ViewEventBinderProvider internal constructor() {

    private val eventBinders = SparseArray<ViewEventBinder>()

    init {
        eventBinders.append(R.id.event_on_click, OnClickListenerBinder())
        eventBinders.append(R.id.event_on_long_click, OnLongClickListenerBinder())
        eventBinders.append(R.id.event_on_item_selected, OnItemSelectedListenerBinder())
    }

    fun bind(smartViewHolder: SmartViewHolder<*>, targetView: View, viewEventListener: OnViewEventListener, viewEventId: ViewEventId) {
        if (eventBinders.indexOfKey(viewEventId) >= 0)
            eventBinders.get(viewEventId).bindListener(smartViewHolder, targetView, viewEventListener, viewEventId)
    }

    /*
     * Binder definitions
     */

    internal interface ViewEventBinder {
        fun bindListener(smartViewHolder: SmartViewHolder<*>,
                         targetView: View,
                         viewEventListener: OnViewEventListener,
                         viewEventId: ViewEventId)
    }

    internal class OnClickListenerBinder : ViewEventBinder {
        override fun bindListener(smartViewHolder: SmartViewHolder<*>,
                                  targetView: View,
                                  viewEventListener: OnViewEventListener,
                                  viewEventId: ViewEventId) {
            targetView.setOnClickListener { v -> viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.adapterPosition) }
        }
    }

    internal class OnLongClickListenerBinder : ViewEventBinder {
        override fun bindListener(smartViewHolder: SmartViewHolder<*>,
                                  targetView: View,
                                  viewEventListener: OnViewEventListener,
                                  viewEventId: ViewEventId) {
            targetView.setOnLongClickListener { v ->
                viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.adapterPosition)
                true
            }
        }
    }

    internal class OnItemSelectedListenerBinder : ViewEventBinder {
        @Suppress("UNCHECKED_CAST")
        override fun bindListener(smartViewHolder: SmartViewHolder<*>,
                                  targetView: View,
                                  viewEventListener: OnViewEventListener,
                                  viewEventId: ViewEventId) {

            if (smartViewHolder is StatefulViewHolder<*> && viewEventListener is OnItemSelectedListener) {
                (smartViewHolder as? StatefulViewHolder<SmartStateHolder>)?.stateHolder = viewEventListener.selectionStateHolder
            }

            if ((viewEventListener as OnItemSelectedListener).enableOnLongClick) {
                targetView.setOnLongClickListener { v ->
                    viewEventListener
                            .selectionStateHolder
                            .toggle(smartViewHolder.adapterPosition)
                    viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.adapterPosition)
                    true
                }
            }

            targetView.setOnClickListener { v ->
                if (!viewEventListener.enableOnLongClick
                    || viewEventListener.enableOnLongClick
                    && viewEventListener.selectionStateHolder.selectedItemsCount > 0
                ) {
                    viewEventListener
                        .selectionStateHolder
                        .toggle(smartViewHolder.adapterPosition)
                }
                viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.adapterPosition)
            }
        }
    }
}
