package smartadapter.internal.mapper;

/*
 * Created by Manne Öhlund on 2019-08-02.
 * Copyright (c) All rights reserved.
 */

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;

import smartadapter.R;
import smartadapter.listener.OnItemSelectedListener;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.viewholder.StatefulViewHolder;

/**
 * Provides the view listener binding logic for views.
 */
public class ViewEventBinderProvider {

    private SparseArray<ViewEventBinder> eventBinders = new SparseArray<>();

    ViewEventBinderProvider() {
        eventBinders.append(R.id.event_on_click, new OnClickListenerBinder());
        eventBinders.append(R.id.event_on_long_click, new OnLongClickListenerBinder());
        eventBinders.append(R.id.event_on_item_selected, new OnItemSelectedListenerBinder());
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

    class OnItemSelectedListenerBinder implements ViewEventBinder {
        @Override
        @SuppressWarnings("unchecked")
        public void bindListener(@NonNull SmartViewHolder smartViewHolder,
                                 @NonNull View targetView,
                                 @NonNull OnViewEventListener viewEventListener,
                                 int viewEventId) {

            if (smartViewHolder instanceof StatefulViewHolder) {
                ((StatefulViewHolder) smartViewHolder).setStateHolder(((OnItemSelectedListener) viewEventListener).getSelectionStateHolder());
            }

            if (((OnItemSelectedListener)viewEventListener).enableOnLongClick()) {
                targetView.setOnLongClickListener(v -> {
                    ((OnItemSelectedListener) viewEventListener)
                            .getSelectionStateHolder()
                            .toggle(smartViewHolder.getAdapterPosition());
                    viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.getAdapterPosition());
                    return true;
                });
            }

            targetView.setOnClickListener(v -> {
                if (!((OnItemSelectedListener) viewEventListener).enableOnLongClick() ||
                        ((OnItemSelectedListener)viewEventListener).enableOnLongClick()
                        && ((OnItemSelectedListener) viewEventListener).getSelectionStateHolder().getSelectedItemsCount() > 0) {
                    ((OnItemSelectedListener) viewEventListener)
                            .getSelectionStateHolder()
                            .toggle(smartViewHolder.getAdapterPosition());
                }
                viewEventListener.onViewEvent(v, viewEventId, smartViewHolder.getAdapterPosition());
            });
        }
    }
}
