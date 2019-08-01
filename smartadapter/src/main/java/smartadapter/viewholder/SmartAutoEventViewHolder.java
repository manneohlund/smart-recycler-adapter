package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-11.
 * Copyright (c) All rights reserved.
 */

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import smartadapter.R;
import smartadapter.listener.OnViewActionListener;

public abstract class SmartAutoEventViewHolder<T> extends SmartEventViewHolder<T> {

    public SmartAutoEventViewHolder(View view) {
        super(view);
    }

    @Override
    public void setViewEventListeners(HashMap<Integer, HashMap<Integer, OnViewActionListener>> viewEventListeners) {
        super.setViewEventListeners(viewEventListeners);

        if (viewEventListeners == null) {
            return;
        }

        for (Map.Entry<Integer, HashMap<Integer, OnViewActionListener>> listenerEntry : viewEventListeners.entrySet()) {
            View targetView = itemView;
            int eventViewId = listenerEntry.getKey();
            if (eventViewId != R.id.undefined) {
                targetView = itemView.findViewById(eventViewId);
            }

            for (Map.Entry<Integer, OnViewActionListener> viewEventEntry : listenerEntry.getValue().entrySet()) {
                int viewEventId = viewEventEntry.getKey();
                OnViewActionListener viewEventListener = viewEventEntry.getValue();

                if (viewEventId == R.id.action_on_click) {
                    targetView.setOnClickListener(v ->
                            viewEventListener.onViewEvent(v, viewEventId, getAdapterPosition()));
                } else if (viewEventId == R.id.action_on_long_click) {
                    targetView.setOnLongClickListener(v -> {
                        viewEventListener.onViewEvent(v, viewEventId, getAdapterPosition());
                        return true;
                    });
                }
            }
        }
    }
}
