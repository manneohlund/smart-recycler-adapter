package smartadapter;

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Basic requirements for an ExtensionBuilder.
 * @param <E> extension of {@link SmartRecyclerAdapterExtensionBuilder}
 */
public interface SmartRecyclerAdapterExtensionBuilder<E extends SmartRecyclerAdapterExtensionBuilder> {

    E setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter);

    E setRecyclerView(@NonNull RecyclerView recyclerView);

    void build();
}
