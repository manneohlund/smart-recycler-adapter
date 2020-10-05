package smartadapter.binders

import smartadapter.SmartRecyclerAdapter

/**
 * Defining the smart extension and is the basic interface to implement in extension libraries
 * to bind with [SmartRecyclerAdapter] on adapter creation. Fetch the extension easily with the [identifier].
 */
interface SmartRecyclerAdapterExtension {

    /**
     * Identifies the [SmartRecyclerAdapterExtension] for access in [smartadapter.SmartRecyclerAdapter.smartRecyclerAdapterExtensions].
     */
    val identifier: Any

    /**
     * Builds and binds with supplied [SmartRecyclerAdapter]
     */
    fun bind(smartRecyclerAdapter: SmartRecyclerAdapter)
}