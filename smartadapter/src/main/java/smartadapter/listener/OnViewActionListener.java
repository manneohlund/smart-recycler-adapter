package smartadapter.listener;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View;

import smartadapter.R;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Callback added in {@link smartadapter.SmartRecyclerAdapter} for view actions listening in {@link SmartViewHolder} extensions.
 */
public interface OnViewActionListener {

    /**
     * Default implementation returns {@link SmartViewHolder} class which
     * {@link smartadapter.SmartRecyclerAdapter} will resolve to all {@link SmartViewHolder} extensions.
     *
     * <p>Can be overridden to target specific {@link SmartViewHolder} extension.</p>
     *
     * @return {@link SmartViewHolder}.class
     */
    default Class<? extends SmartViewHolder> getViewHolderType() {
        return SmartViewHolder.class;
    }

    /**
     * Default implementation returns {@link R.id#undefined} which will point to root view of the view holder.
     *
     * <p>Can be overridden to target specific view id.</p>
     *
     * @return {@link R.id#undefined}
     */
    default int getViewId() {
        return R.id.undefined;
    }

    /**
     * Default implementation returns {@link R.id#undefined} and any {@link SmartViewHolder} extensions must implement ViewEventHolder.
     *
     * <p>Can be overridden with predefined view action ids.</p>
     * {@link R.id#action_on_click}
     * {@link R.id#action_on_long_click}
     *
     * @return {@link R.id#undefined}
     */
    default int getViewActionId() {
        return R.id.undefined;
    }

    /**
     * Receiver of a view action.
     *
     * @param view source view which dispatched the action
     * @param actionId callback actionId defined in {@link #getViewActionId()}
     * @param position the adapter position
     */
    void onViewEvent(View view, int actionId, int position);
}
