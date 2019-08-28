package smartadapter.listener;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View;

import androidx.annotation.NonNull;

import io.github.manneohlund.smartrecycleradapter.R;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.viewholder.ViewEventListenerHolder;

/**
 * Callback added in {@link smartadapter.SmartRecyclerAdapter} for view events listening in {@link SmartViewHolder} extensions.
 */
public interface OnViewEventListener {

    /**
     * Default implementation returns {@link SmartViewHolder} class which
     * {@link smartadapter.SmartRecyclerAdapter} will bind to all {@link SmartViewHolder} extensions.
     *
     * <p>Can be overridden to a specific target {@link SmartViewHolder} extension.</p>
     *
     * @return {@link SmartViewHolder}.class
     */
    @NonNull
    default Class<? extends SmartViewHolder> getViewHolderType() {
        return SmartViewHolder.class;
    }

    /**
     * Default implementation returns {@link R.id#undefined} which will point to the root view of the view in the view holder.
     *
     * <p>Can be overridden to target specific view id.</p>
     *
     * @return {@link R.id#undefined}
     */
    default int getViewId() {
        return R.id.undefined;
    }

    /**
     * Default implementation returns {@link R.id#undefined} and any {@link SmartViewHolder} extensions must implement {@link ViewEventListenerHolder}.
     *
     * <p>Can be overridden with predefined view action ids.</p>
     * {@link R.id#event_on_click}
     * {@link R.id#event_on_long_click}
     *
     * @return {@link R.id#undefined}
     */
    default int getViewEventId() {
        return R.id.undefined;
    }

    /**
     * Receiver of a view events.
     *
     * @param view source view which dispatched the action
     * @param actionId callback actionId defined in {@link #getViewEventId()}
     * @param position the adapter position
     */
    void onViewEvent(@NonNull View view, int actionId, int position);
}
