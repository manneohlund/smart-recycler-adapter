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
     * Will be set from [smartadapter.SmartRecyclerAdapter]
     * if a default global listener has been added to the adapter
     * or target view holder is defined by [OnViewEventListener.getViewHolderType].
     *
     * @see OnViewEventListener
     */
    var viewEventListener: OnViewEventListener
}
