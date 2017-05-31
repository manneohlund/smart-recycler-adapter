package smartadapter.manager;

import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import smartadapter.listener.ViewEventListener;
import smartadapter.utils.ReflectionUtils;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Created by Manne Öhlund on 30/05/17.
 * Copyright © 2017 All rights reserved.
 */

public class Mapper {

    private final Object caller;

    private int identifier = 0;
    private final SparseArray<Class<? extends SmartViewHolder>> viewTypeMapper = new SparseArray<>();
    private final HashMap<Class, Constructor> viewHolderConstructorMapper = new HashMap<>();
    private HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper = new HashMap<>();

    // Optimization for constructor parameters
    private Class<? extends SmartViewHolder> vhc;

    public Mapper(Object callerEnclosingClass) {
        this.caller = callerEnclosingClass;
    }

    // Sparse array
    public int getItemViewType(ViewTypeResolver viewTypeResolver, Object item, int position) {
        // Check if viewTypeResolver is assigned and contains the viewHolder
        Class<? extends SmartViewHolder> viewHolder = viewTypeResolver == null ? null : viewTypeResolver.getViewType(item, position);
        if (viewHolder == null) {
            viewHolder = dataTypeViewHolderMapper.get(item.getClass().getName());
        } else {
            dataTypeViewHolderMapper.put(item.getClass().getName(), viewHolder);
        }

        // If viewHolder was found, proceed with view id mapping
        if (viewHolder != null) {
            // Add the id if not existing
            if (viewTypeMapper.indexOfValue(viewHolder) < 0) {
                viewTypeMapper.put(identifier++, viewHolder);
            }
            // Return mapping key
            Class<? extends SmartViewHolder> viewType;
            for (int i = 0; i < viewTypeMapper.size(); i++) {
                viewType = viewTypeMapper.get(i);
                if (viewType.getName().equals(viewHolder.getName())) {
                    return viewTypeMapper.keyAt(i);
                }
            }
        }

        throw new RuntimeException(String.format("Fatal error! Mapping of ViewHolder to item '%s' does not exist", item.getClass().getName()));
    }

    public <C extends SmartViewHolder> C getViewHolder(ViewEventListener viewEventListener, ViewGroup parent, int viewType) {
        Constructor constructor;
        try {
            vhc = viewTypeMapper.get(viewType);
            if (!viewHolderConstructorMapper.containsKey(vhc)) {
                constructor = ReflectionUtils.getConstructor(vhc,
                        ReflectionUtils.isNonStaticInnerClass(vhc) ?
                                new Class[]{ caller.getClass(), ViewGroup.class } :
                                new Class[]{ ViewGroup.class }
                );
                viewHolderConstructorMapper.put(vhc, constructor);
            } else {
                constructor = viewHolderConstructorMapper.get(vhc);
            }

            //constructor = viewHolderConstructorMapper.get(viewHolderClass); // TODO: 31/05/17
            C viewHolder = (C) ReflectionUtils.invokeConstructor(vhc, constructor,
                    ReflectionUtils.isNonStaticInnerClass(vhc) ?
                            new Object[]{caller, parent} :
                            new Object[]{parent}
            );
            viewHolder.setViewEventListener(viewEventListener);
            return viewHolder;
        } catch (Exception e) {
            throw new RuntimeException(String.format("'%s' must have a constructor which take 'ViewGroup parent' as param", viewTypeMapper.get(viewType).toString()));
        }
    }


    public void addMapping(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        dataTypeViewHolderMapper.put(itemType.getName(), viewHolderType);
    }

    public void setDataTypeViewHolderMapper(HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper) {
        this.dataTypeViewHolderMapper = dataTypeViewHolderMapper;
    }
}
