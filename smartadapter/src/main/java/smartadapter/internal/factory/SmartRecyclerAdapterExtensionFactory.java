package smartadapter.internal.factory;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.SmartRecyclerAdapterExtensionBuilder;

/**
 * Builds {@link SmartRecyclerAdapterExtensionBuilder} extensions.
 *
 * @see smartadapter.widget.SwipeExtension
 * @see smartadapter.widget.DragAndDropExtension
 */
public class SmartRecyclerAdapterExtensionFactory {

    private List<SmartRecyclerAdapterExtensionBuilder> smartRecyclerAdapterExtensions = new ArrayList<>();

    public void add(@NonNull SmartRecyclerAdapterExtensionBuilder smartExtensionBuilder) {
        smartRecyclerAdapterExtensions.add(smartExtensionBuilder);
    }

    public void build(@NonNull SmartRecyclerAdapter smartRecyclerAdapter, @NonNull RecyclerView recyclerView) {
        for (SmartRecyclerAdapterExtensionBuilder smartRecyclerAdapterExtension : smartRecyclerAdapterExtensions) {
            smartRecyclerAdapterExtension
                    .setSmartRecyclerAdapter(smartRecyclerAdapter)
                    .setRecyclerView(recyclerView)
                    .build();
        }
    }
}
