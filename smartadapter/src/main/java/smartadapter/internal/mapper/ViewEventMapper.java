package smartadapter.internal.mapper;

/*
 * Created by Manne Öhlund on 2019-08-01.
 * Copyright (c) All rights reserved.
 */

import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.HashMap;

import smartadapter.R;
import smartadapter.SmartRecyclerAdapter;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewEventListenerHolder;
import smartadapter.viewholder.SmartViewHolder;

import static smartadapter.internal.utils.ViewEventValidator.isViewEventIdValid;

/**
 * Maps and binds view events with view holder target views with {@link ViewEventBinderProvider}.
 */
public class ViewEventMapper {

    private HashMap<Class<? extends SmartViewHolder>, SparseArray<SparseArray<OnViewEventListener>>> viewEventListenerMap = new HashMap<>();
    private ViewEventBinderProvider viewEventListenerMapperProvider = new ViewEventBinderProvider();

    /**
     * Adds {@link OnViewEventListener} to the {@link SmartRecyclerAdapter}.
     * The adapter will then automatically map the {@link OnViewEventListener} to the target view holder class with {@link OnViewEventListener#getViewHolderType()},
     * set the viewEventListener on the right View with viewId using {@link OnViewEventListener#getViewId()}.
     *
     * @see OnViewEventListener#getViewEventId() for predefined eventds.
     *
     * @param viewEventListener target OnViewEventListener
     */
    public final void addViewEventListener(OnViewEventListener viewEventListener) {
        if (!isViewEventIdValid(viewEventListener.getViewEventId()))
            throw new RuntimeException(String.format("Invalid view event id (%d) for ViewHolder (%s)", viewEventListener.getViewEventId(), viewEventListener.getViewHolderType()));

        SparseArray<SparseArray<OnViewEventListener>> mapper;
        if ((mapper = viewEventListenerMap.get(viewEventListener.getViewHolderType())) == null) {
            mapper = new SparseArray<>();
        }
        if (mapper.indexOfKey(viewEventListener.getViewId()) < 0) {
            final SparseArray<OnViewEventListener> viewEventAndListenerMap = new SparseArray<>();
            viewEventAndListenerMap.put(viewEventListener.getViewEventId(), viewEventListener);
            mapper.put(viewEventListener.getViewId(), viewEventAndListenerMap);
        }
        mapper.get(viewEventListener.getViewId()).put(viewEventListener.getViewEventId(), viewEventListener);
        this.viewEventListenerMap.put(viewEventListener.getViewHolderType(), mapper);
    }

    public HashMap<Class<? extends SmartViewHolder>, SparseArray<SparseArray<OnViewEventListener>>> getViewEventListenerMap() {
        return viewEventListenerMap;
    }

    public void mapViewActionWith(@NonNull SmartViewHolder smartViewHolder) {
        mapViewActionWith(smartViewHolder, viewEventListenerMap.get(SmartViewHolder.class));
        mapViewActionWith(smartViewHolder, viewEventListenerMap.get(smartViewHolder.getClass()));
    }

    private  void mapViewActionWith(@NonNull SmartViewHolder smartViewHolder, SparseArray<SparseArray<OnViewEventListener>> viewIdActionIdMap) {
        if (viewIdActionIdMap != null) {
            for (int i = 0; i < viewIdActionIdMap.size(); i++) {
                SparseArray<OnViewEventListener> eventIdAndListener = viewIdActionIdMap.valueAt(i);
                for (int j = 0; j < eventIdAndListener.size(); j++) {
                    int viewId = viewIdActionIdMap.keyAt(i);
                    int viewEventId = eventIdAndListener.keyAt(j);
                    OnViewEventListener viewActionListener = eventIdAndListener.valueAt(j);

                    if (viewId == R.id.undefined &&
                            viewEventId == R.id.undefined) {
                        if (SmartViewEventListenerHolder.class.isAssignableFrom(smartViewHolder.getClass())) {
                            ((SmartViewEventListenerHolder)smartViewHolder).setOnViewEventListener(viewActionListener);
                        } else {
                            Log.e(ViewEventMapper.class.getName(), String.format(
                                    "Don't forget that '%s' needs to implement '%s' in order to receive the events",
                                    smartViewHolder.getClass().getName(),
                                    SmartViewEventListenerHolder.class.getName()));
                        }
                    }

                    View targetView = smartViewHolder.itemView;
                    if (viewId != R.id.undefined) {
                        targetView = smartViewHolder.itemView.findViewById(viewId);
                    }

                    viewEventListenerMapperProvider.bind(smartViewHolder, targetView, viewActionListener, viewEventId);
                }
            }
        }
    }
}
