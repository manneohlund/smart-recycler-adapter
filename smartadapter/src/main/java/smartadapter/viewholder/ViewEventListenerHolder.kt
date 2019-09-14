package smartadapter.viewholder

/*
 * Created by Manne Öhlund on 2019-08-05.
 * Copyright (c) All rights reserved.
 */

import smartadapter.listener.OnViewEventListener

/**
 * Lets a view holder handle events with custom event ids.
 * Implement this interface in your [SmartViewHolder] extension.
 */
interface ViewEventListenerHolder {

    /**
     * Will be called from [smartadapter.SmartRecyclerAdapter] and
     * will set [OnViewEventListener] if a default global listener is added to the adapter or
     * target view holder is defined by [OnViewEventListener.getViewHolderType].
     *
     * @see OnViewEventListener
     *
     * @param viewEventListener listener for view events
     */
    fun setOnViewEventListener(viewEventListener: OnViewEventListener)
}
