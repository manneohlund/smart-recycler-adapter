package smartadapter.internal.mapper;

/*
 * Created by Manne Öhlund on 2019-08-02.
 * Copyright (c) All rights reserved.
 */

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;

import smartadapter.R;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewHolder;

/**
 * Provides the view listener binding logic for views.
 */
public class ViewEventBinderProvider {

    private SparseArray<ViewEventBinder> eventBinders = new SparseArray<>();

    ViewEventBinderProvider() {
        eventBinders.append(R.id.event_on_click, new OnClickListenerBinder());
        eventBinders.append(R.id.event_on_long_click, new OnLongClickListenerBinder());
    }

    public void bind(SmartViewHolder smartViewHolder, View targetView, OnViewEventListener viewEventListener, int viewEventId) {
        if (eventBinders.indexOfKey(viewEventId) >= 0)
            eventBinders.get(viewEventId).bindListener(smartViewHolder, targetView, viewEventListener, viewEventId);
    }

    /*
     * Binder definitions
     */

    interface ViewEventBinder {
        void bindListener(@NonNull SmartViewHolder smartViewHolder,
                          @NonNull View targetView,
                          @NonNull OnViewEventListener viewEventListener,
                          int viewEventId);
    }

    class OnClickListenerBinder implements ViewEventBinder {
        @Override
        public void bindListener(@NonNull SmartViewHolder smartViewHolder,
                                 @NonNull View targetView,
                                 @NonNull OnViewEventListener viewEventListener,
                                 int viewEventId) {
            targetView.setOnClickListener(v ->
                    viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.getAdapterPosition()));
        }
    }

    class OnLongClickListenerBinder implements ViewEventBinder {
        @Override
        public void bindListener(@NonNull SmartViewHolder smartViewHolder,
                                 @NonNull View targetView,
                                 @NonNull OnViewEventListener viewEventListener,
                                 int viewEventId) {
            targetView.setOnLongClickListener(v -> {
                viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.getAdapterPosition());
                return true;
            });
        }
    }
}
