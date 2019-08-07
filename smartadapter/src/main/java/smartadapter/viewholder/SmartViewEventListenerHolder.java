package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-08-05.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;

import smartadapter.listener.OnViewEventListener;

/**
 * Lets a view holder handle events with custom event ids.
 * Implement this interface in your {@link SmartViewHolder} extension.
 */
public interface SmartViewEventListenerHolder {

    /**
     * Will be called from {@link smartadapter.SmartRecyclerAdapter} and
     * will set {@link OnViewEventListener} if a default global listener is added to the adapter or
     * target view holder is defined by {@link OnViewEventListener#getViewHolderType()}.
     *
     * @see OnViewEventListener
     * @param viewEventListener listener for view events
     */
    void setOnViewEventListener(@NonNull OnViewEventListener viewEventListener);
}
