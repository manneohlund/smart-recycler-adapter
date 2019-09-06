package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.util.Log
import android.util.SparseArray
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.SmartRecyclerAdapter
import smartadapter.internal.utils.ViewEventValidator.isViewEventIdValid
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import java.util.*
import kotlin.reflect.KClass

/**
 * Maps and binds view events with view holder target views with [ViewEventBinderProvider].
 */
class ViewEventMapper {

    val viewEventListenerMap = HashMap<KClass<out SmartViewHolder<*>>, SparseArray<SparseArray<OnViewEventListener>>>()
    private val viewEventListenerMapperProvider = ViewEventBinderProvider()

    /**
     * Adds [OnViewEventListener] to the [SmartRecyclerAdapter].
     * The adapter will then automatically map the [OnViewEventListener] to the target view holder class with [OnViewEventListener.viewHolderType],
     * set the viewEventListener on the right View with viewId using [OnViewEventListener.viewId].
     *
     * @see OnViewEventListener.viewEventId
     * @param viewEventListener target OnViewEventListener
     */
    fun addViewEventListener(viewEventListener: OnViewEventListener) {
        if (!isViewEventIdValid(viewEventListener.viewEventId))
            throw RuntimeException(String.format("Invalid view event id (%d) for ViewHolder (%s)", viewEventListener.viewEventId, viewEventListener.viewHolderType))

        var mapper: SparseArray<SparseArray<OnViewEventListener>>?
        mapper = viewEventListenerMap[viewEventListener.viewHolderType]
        if (mapper == null) {
            mapper = SparseArray()
        }
        if (mapper.indexOfKey(viewEventListener.viewId) < 0) {
            val viewEventAndListenerMap = SparseArray<OnViewEventListener>()
            viewEventAndListenerMap.put(viewEventListener.viewEventId, viewEventListener)
            mapper.put(viewEventListener.viewId, viewEventAndListenerMap)
        }
        mapper.get(viewEventListener.viewId).put(viewEventListener.viewEventId, viewEventListener)
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

    private fun mapViewEventWith(smartViewHolder: SmartViewHolder<*>, viewIdviewEventIdMap: SparseArray<SparseArray<OnViewEventListener>>?) {
        if (viewIdviewEventIdMap != null) {
            for (i in 0 until viewIdviewEventIdMap.size()) {
                val eventIdAndListener = viewIdviewEventIdMap.valueAt(i)
                for (j in 0 until eventIdAndListener.size()) {
                    val viewId = viewIdviewEventIdMap.keyAt(i)
                    val viewEventId = eventIdAndListener.keyAt(j)
                    val viewActionListener = eventIdAndListener.valueAt(j)

                    if (viewId == R.id.undefined && viewEventId == R.id.undefined) {
                        if (ViewEventListenerHolder::class.java.isAssignableFrom(smartViewHolder.javaClass)) {
                            (smartViewHolder as ViewEventListenerHolder).setOnViewEventListener(viewActionListener)
                        } else {
                            Log.e(ViewEventMapper::class.java.name, String.format(
                                    "Don't forget that '%s' needs to implement '%s' in order to receive the events",
                                    smartViewHolder.javaClass.name,
                                    ViewEventListenerHolder::class.java.name))
                        }
                    }

                    var targetView = smartViewHolder.itemView
                    if (viewId != R.id.undefined) {
                        targetView = smartViewHolder.itemView.findViewById(viewId)
                    }

                    viewEventListenerMapperProvider.bind(smartViewHolder, targetView, viewActionListener, viewEventId)
                }
            }
        }
    }
}
