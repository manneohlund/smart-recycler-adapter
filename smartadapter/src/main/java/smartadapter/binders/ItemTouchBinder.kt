package smartadapter.binders

import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartRecyclerAdapter

interface ItemTouchBinder<T> {

    /**
     * Builds and binds the drag and drop mechanism to target recycler view
     */
    fun bind(
        smartRecyclerAdapter: SmartRecyclerAdapter,
        recyclerView: RecyclerView
    ) : T
}
