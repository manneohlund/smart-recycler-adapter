package smartadapter.internal;

/*
 * Created by Manne Öhlund on 2019-07-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;

import smartadapter.viewholder.SmartViewHolder;

public class ViewHolderConstructorMapper {

    private final HashMap<Class, Constructor> viewHolderConstructorMapper = new HashMap<>();

    public void add(Collection<Class<? extends SmartViewHolder>> smartViewHolderClasses) {
        for (Class<? extends SmartViewHolder> smartViewHolderClass : smartViewHolderClasses) {
            add(smartViewHolderClass);
        }
    }

    public void add(Class<? extends SmartViewHolder> smartViewHolderClass) {
        if (!viewHolderConstructorMapper.containsKey(smartViewHolderClass)) {
            viewHolderConstructorMapper.put(
                    smartViewHolderClass,
                    ReflectionUtils.getConstructor(smartViewHolderClass, View.class, ViewGroup.class));
        }
    }

    public Constructor getConstructor(Class<? extends SmartViewHolder> smartViewHolderClass) {
        return viewHolderConstructorMapper.get(smartViewHolderClass);
    }
}
