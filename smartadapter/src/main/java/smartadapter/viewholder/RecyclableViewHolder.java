package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright (c). All rights reserved.
 */

import android.support.v7.widget.RecyclerView;

/**
 * Lets you decide if a ViewHolder created by the Adapter should be recycled due to its transient state.
 *
 * <p>Will be called from {@link android.support.v7.widget.RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)}.</p>
 */
public interface RecyclableViewHolder {

    /**
     * @see android.support.v7.widget.RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)
     * 
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
     * the View and recycle it regardless. If this method returns <code>false</code>,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    boolean onFailedToRecycleView();
}
