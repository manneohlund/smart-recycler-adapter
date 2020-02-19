package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.util.SparseArray
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.listener.OnViewEventListener
import smartadapter.listener.ViewEvent
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import java.util.HashMap

/**
 * Maps and binds view events with view holder target views with [ViewEventBinderProvider].
 */
class ViewEventMapper() {

    lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    val viewEventListenerMap = HashMap<SmartViewHolderType, SparseArray<SparseArray<OnViewEventListener<*>>>>()
    private val viewEventListenerMapperProvider = ViewEventBinderProvider()

    /**
     * Adds [OnViewEventListener] to the [SmartRecyclerAdapter].
     * The adapter will then automatically map the [OnViewEventListener] to the target view holder class with [OnViewEventListener.viewHolderType],
     * set the viewEventListener on the right View with viewId using [OnViewEventListener.viewId].
     *
     * @see OnViewEventListener.viewEventId
     * @param viewEventListener target OnViewEventListener
     */
    fun addViewEventListener(viewEventListener: OnViewEventListener<*>) {
        var mapper: SparseArray<SparseArray<OnViewEventListener<*>>>?
        mapper = viewEventListenerMap[viewEventListener.viewHolderType]
        if (mapper == null) {
            mapper = SparseArray()
        }
        if (mapper.indexOfKey(viewEventListener.viewId) < 0) {
            val viewEventAndListenerMap = SparseArray<OnViewEventListener<*>>()
            viewEventAndListenerMap.put(viewEventListenerMapperProvider.resolveViewEventId(viewEventListener), viewEventListener)
            mapper.put(viewEventListener.viewId, viewEventAndListenerMap)
        }
        mapper.get(viewEventListener.viewId).put(viewEventListenerMapperProvider.resolveViewEventId(viewEventListener), viewEventListener)
        this.viewEventListenerMap[viewEventListener.viewHolderType] = mapper
    }

    fun mapViewEventWith(smartViewHolder: SmartViewHolder<*>) {
        mapViewEventWith(smartViewHolder, viewEventListenerMap[SmartViewHolder::class])

        for ((key, value) in viewEventListenerMap) {
            val isNotOfSmartViewHolder = key != SmartViewHolder::class
            val isTargetClass = key.java.isAssignableFrom(smartViewHolder::class.java)
            if (isNotOfSmartViewHolder && isTargetClass) {
                mapViewEventWith(smartViewHolder, value)
            }
        }
    }

    private fun mapViewEventWith(
        smartViewHolder: SmartViewHolder<*>,
        viewIdViewEventIdMap: SparseArray<SparseArray<OnViewEventListener<*>>>?
    ) {
        if (viewIdViewEventIdMap != null) {
            for (i in 0 until viewIdViewEventIdMap.size()) {
                val eventIdAndListener = viewIdViewEventIdMap.valueAt(i)
                for (j in 0 until eventIdAndListener.size()) {
                    val viewId = viewIdViewEventIdMap.keyAt(i)
                    val viewEventId = eventIdAndListener.keyAt(j)
                    val viewEventListener = eventIdAndListener.valueAt(j)

                    if (viewId == R.id.undefined && smartViewHolder is ViewEventListenerHolder<*> && viewEventListener.listener is ViewEvent) {
                        (smartViewHolder as? ViewEventListenerHolder<ViewEvent>)?.let { viewEventListenerHolder ->
                            (viewEventListener.listener as? ViewEvent)?.let {
                                viewEventListenerHolder.viewEventListener = it
                            }
                        }
                    } else {
                        var targetView = smartViewHolder.itemView
                        if (viewId != R.id.undefined) {
                            targetView = smartViewHolder.itemView.findViewById(viewId)
                        }

                        viewEventListenerMapperProvider.bind(smartRecyclerAdapter, smartViewHolder, targetView, viewEventListener, viewEventId)
                    }
                }
            }
        }
    }
}
