package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-16.
 * Copyright (c) All rights reserved.
 */

import androidx.annotation.NonNull;

import smartadapter.SmartRecyclerAdapter;

public interface SmartAdapterHolder {
    void setSmartRecyclerAdapter(@NonNull SmartRecyclerAdapter smartRecyclerAdapter);
}
