package smartadapter.widget;

/*
 * Created by Manne Öhlund on 2019-08-21.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import smartadapter.SmartExtensionBuilder;
import smartadapter.SmartRecyclerAdapter;

public class DiffUtilExtensionBuilder implements SmartExtensionBuilder<DiffUtilExtension, DiffUtilExtensionBuilder> {

    private DiffUtilExtension diffUtilExtension;
    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerView recyclerView;
    private DiffUtilExtension.DiffPredicate diffPredicate;

    public DiffUtilExtensionBuilder() {
        this.diffUtilExtension = new DefaultDiffUtilExtension();
    }

    public DiffUtilExtensionBuilder(@NonNull DiffUtilExtension diffUtilExtension) {
        this.diffUtilExtension = diffUtilExtension;
    }

    @Override
    public DiffUtilExtensionBuilder setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter;
        return this;
    }

    @Override
    public DiffUtilExtensionBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public DiffUtilExtensionBuilder setDiffPredicate(@NonNull DiffUtilExtension.DiffPredicate diffPredicate) {
        this.diffPredicate = diffPredicate;
        return this;
    }

    @Override
    public DiffUtilExtension build() {
        diffUtilExtension.setSmartRecyclerAdapter(smartRecyclerAdapter);
        diffUtilExtension.setDiffPredicate(diffPredicate);
        return diffUtilExtension;
    }
}
