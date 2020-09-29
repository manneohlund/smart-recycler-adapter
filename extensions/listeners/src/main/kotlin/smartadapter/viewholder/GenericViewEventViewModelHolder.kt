package smartadapter.viewholder

/*
 * Created by Manne Öhlund on 2020-01-08.
 * Copyright (c) All rights reserved.
 */

import smartadapter.listeners.OnCustomViewEventListener

/**
 * Lets a view holder handle events with custom event ids.
 * Implement this interface in your [SmartViewHolder] extension.
 */
interface GenericViewEventViewModelHolder {

    /**
     * Will be set from corresponding [OnCustomViewEventListener]
     *
     * @see OnCustomViewEventListener
     */
    var customViewEventListener: OnCustomViewEventListener
}