package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-16.
 * Copyright (c) All rights reserved.
 */

import android.support.annotation.NonNull;

import smartadapter.SmartAdapterBuilder;
import smartadapter.SmartRecyclerAdapter;

public interface SmartAdapterHolder {
    @NonNull SmartRecyclerAdapter getSmartRecyclerAdapter();
    void setSmartAdapterBuilder(@NonNull SmartAdapterBuilder smartAdapterBuilder);
}
