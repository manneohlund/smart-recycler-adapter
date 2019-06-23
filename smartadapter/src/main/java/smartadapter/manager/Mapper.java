package smartadapter.manager;

/*
 * Created by Manne Öhlund on 30/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import smartadapter.SmartAdapterBuilder;
import smartadapter.listener.ViewEventListener;
import smartadapter.utils.ReflectionUtils;
import smartadapter.viewholder.SmartAdapterHolder;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.viewholder.ViewEventHolder;
import smartadapter.widget.ViewTypeResolver;

/**
 * Responsible for mapping subtype of SmartViewHolder with target data type.
 */
public class Mapper {

    private final Object caller;

    private int identifier = 0;
    private final SparseArray<Class<? extends SmartViewHolder>> viewTypeMapper = new SparseArray<>();
    private final HashMap<Class, Constructor> viewHolderConstructorMapper = new HashMap<>();
    private HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper = new HashMap<>();
    private HashMap<Class<? extends SmartViewHolder>, SmartAdapterBuilder> smartAdapterBuilderMapper = new HashMap<>();

    public Mapper(Object callerEnclosingClass) {
        this.caller = callerEnclosingClass;
    }

    /**
     * Will first check if viewTypeResolver is assigned and contains the viewHolder.
     * If viewHolder was found, proceed with view id mapping.
     * - Add the id if not existing
     * - Return mapping key
     *
     * @param viewTypeResolver custom view holder type resolver
     * @param item target item
     * @param position adapter position
     * @throws RuntimeException if mapping/relation of ViewHolder to data item was not found
     * @return target view holder type identifier
     */
    public int getItemViewType(ViewTypeResolver viewTypeResolver, Object item, int position) {
        Class<? extends SmartViewHolder> viewHolder = viewTypeResolver == null ? null : viewTypeResolver.getViewType(item, position);
        if (viewHolder == null) {
            viewHolder = dataTypeViewHolderMapper.get(item.getClass().getName());
        }

        if (viewHolder != null) {
            if (viewTypeMapper.indexOfValue(viewHolder) < 0) {
                viewTypeMapper.put(identifier++, viewHolder);
            }
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

    /**
     * Instantiates the target view holder and set view event listeners if specified.
     *
     * @param viewEventListeners Contains general purpose or view id specific event listeners.
     * @param parent Parent ViewGroup
     * @param viewType View holder type
     * @param <VH> Subtype of SmartViewHolder
     * @return Target view holder
     */
    public <VH extends SmartViewHolder> VH createViewHolder(
            HashMap<Class<? extends SmartViewHolder>, HashMap<Integer, HashMap<Integer, ViewEventListener>>> viewEventListeners,
            ViewGroup parent,
            int viewType) {
        Constructor constructor;
        VH viewHolder;
        Class<? extends SmartViewHolder> smartViewHolderConstructor;
        try {
            smartViewHolderConstructor = viewTypeMapper.get(viewType);
            if (!viewHolderConstructorMapper.containsKey(smartViewHolderConstructor)) {
                constructor = ReflectionUtils.getConstructor(smartViewHolderConstructor,
                        ReflectionUtils.isNonStaticInnerClass(smartViewHolderConstructor) ?
                                new Class[]{ caller.getClass(), ViewGroup.class } :
                                new Class[]{ ViewGroup.class }
                );
                viewHolderConstructorMapper.put(smartViewHolderConstructor, constructor);
            } else {
                constructor = viewHolderConstructorMapper.get(smartViewHolderConstructor);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("'%s' must have a constructor which take 'ViewGroup parent' as param or " + e.getMessage(), viewTypeMapper.get(viewType).toString()));
        }

        try {
            viewHolder = (VH) ReflectionUtils.invokeConstructor(smartViewHolderConstructor, constructor,
                    ReflectionUtils.isNonStaticInnerClass(smartViewHolderConstructor)
                            ? new Object[]{caller, parent}
                            : new Object[]{parent}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Error in '%s' constructor: " + e.getCause(), viewTypeMapper.get(viewType).toString()));
        }

        /*
         * Set view event listeners.
         * First check if viewEventListeners has general SmartViewHolder event listener.
         * Then if viewEventListeners has viewHolder.getClass() event listener, will override smartListener.
         */
        if (viewHolder instanceof ViewEventHolder) {
            ((ViewEventHolder)viewHolder).setViewEventListeners(viewEventListeners.get(SmartViewHolder.class));
            ((ViewEventHolder)viewHolder).setViewEventListeners(viewEventListeners.get(viewHolder.getClass()));
        }

        if (viewHolder instanceof SmartAdapterHolder && smartAdapterBuilderMapper.containsKey(viewHolder.getClass())) {
            ((SmartAdapterHolder)viewHolder).setSmartAdapterBuilder(smartAdapterBuilderMapper.get(viewHolder.getClass()));
        }

        return viewHolder;
    }

    public void addMapping(Class<?> itemType, Class<? extends SmartViewHolder> viewHolderType) {
        dataTypeViewHolderMapper.put(itemType.getName(), viewHolderType);
    }

    public void setDataTypeViewHolderMapper(HashMap<String, Class<? extends SmartViewHolder>> dataTypeViewHolderMapper) {
        this.dataTypeViewHolderMapper = dataTypeViewHolderMapper;
    }

    public void setSmartAdapterBuilderMapper(HashMap<Class<? extends SmartViewHolder>, SmartAdapterBuilder> smartAdapterBuilderMapper) {
        this.smartAdapterBuilderMapper = smartAdapterBuilderMapper;
    }
}
