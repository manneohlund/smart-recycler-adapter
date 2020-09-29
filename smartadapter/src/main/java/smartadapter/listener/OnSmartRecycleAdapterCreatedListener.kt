package smartadapter.listener

/*
 * Created by Manne Öhlund on 2020-09-23.
 * Copyright (c) All rights reserved.
 */

import smartadapter.SmartRecyclerAdapter

/**
 * Listener called when the [SmartRecyclerAdapter] has been created in init.
 *
 * Invoked from [smartadapter.SmartRecyclerAdapter] init and should be implemented in a [smartadapter.SmartViewHolderBinder] extension.
 *
 */
interface OnSmartRecycleAdapterCreatedListener {

    /**
     * Called when a [SmartRecyclerAdapter] has been created.
     * @param adapter target [SmartRecyclerAdapter]
     */
    fun onSmartRecycleAdapterCreated(adapter: SmartRecyclerAdapter)
}
