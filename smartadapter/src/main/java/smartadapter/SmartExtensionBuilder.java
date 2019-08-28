package smartadapter;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Basic requirements for an ExtensionBuilder.
 * @param <T> extension type returned by B (SmartExtensionBuilder)
 * @param <B> extension of {@link SmartExtensionBuilder}
 */
public interface SmartExtensionBuilder<T, B extends SmartExtensionBuilder> {

    B setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter);

    B setRecyclerView(@NonNull RecyclerView recyclerView);

    T build();
}
