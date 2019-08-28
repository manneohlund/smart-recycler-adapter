package smartadapter.internal.factory;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartExtensionBuilder;
import smartadapter.SmartRecyclerAdapter;
import smartadapter.widget.BasicDragAndDropExtension;
import smartadapter.widget.BasicSwipeExtension;

/**
 * Builds {@link SmartExtensionBuilder} extensions.
 *
 * @see BasicSwipeExtension
 * @see BasicDragAndDropExtension
 */
public class SmartRecyclerAdapterExtensionFactory {

    private List<SmartExtensionBuilder> smartExtensionBuilders = new ArrayList<>();

    public void add(@NonNull SmartExtensionBuilder smartExtensionBuilder) {
        smartExtensionBuilders.add(smartExtensionBuilder);
    }

    public void build(@NonNull SmartRecyclerAdapter smartRecyclerAdapter, @NonNull RecyclerView recyclerView) {
        for (SmartExtensionBuilder smartExtension : smartExtensionBuilders) {
            smartExtension
                    .setSmartRecyclerAdapter(smartRecyclerAdapter)
                    .setRecyclerView(recyclerView)
                    .build();
        }
    }
}
