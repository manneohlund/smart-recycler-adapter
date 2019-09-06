package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 30/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.util.SparseArray
import android.view.ViewGroup
import smartadapter.SmartRecyclerAdapter
import smartadapter.internal.utils.ReflectionUtils
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.widget.ViewTypeResolver
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * Responsible for mapping subtype of SmartViewHolder with target data type.
 */
class ViewHolderMapper {

    private var smartViewHolderClass: KClass<out SmartViewHolder<*>>? = null
    private var constructor: KFunction<Any>? = null
    private val viewTypeMapper = SparseArray<KClass<out SmartViewHolder<*>>>()
    private val viewHolderConstructorMapper = ViewHolderConstructorMapper()
    private var dataTypeViewHolderMapper = HashMap<String, KClass<out SmartViewHolder<*>>>()
    private var smartRecyclerAdapterMapper = HashMap<KClass<out SmartViewHolder<*>>, SmartRecyclerAdapter>()

    /**
     * Will first check if viewTypeResolver is assigned and contains the smartViewHolderClass.
     * If smartViewHolderClass was found, proceed with view id mapping.
     * - Add the id if not existing
     * - Return mapping smartViewHolderKey
     *
     * @param viewTypeResolver custom view holder type resolver
     * @param item target item
     * @param position adapter position
     * @throws RuntimeException if mapping/relation of ViewHolder to data item was not found
     * @return target view holder type identifier
     */
    fun getItemViewType(viewTypeResolver: ViewTypeResolver?, item: Any, position: Int): Int {
        smartViewHolderClass = viewTypeResolver?.getViewType(item, position)
        if (smartViewHolderClass == null) {
            smartViewHolderClass = dataTypeViewHolderMapper[item.javaClass.name]
        } else {
            viewHolderConstructorMapper.add(smartViewHolderClass!!)
        }
        if (smartViewHolderClass != null) {
            return viewTypeMapper.put(smartViewHolderClass!!)
        }

        throw RuntimeException(String.format("Fatal error! Mapping of ViewHolder to item '%s' does not exist", item.javaClass.name))
    }

    /**
     * Instantiates the target view holder and set view event listeners if specified.
     *
     * @param parent Parent ViewGroup
     * @param viewType View holder type
     * @param <VH> Subtype of SmartViewHolder
     * @return Target view holder
     */
    @Suppress("UNCHECKED_CAST")
    fun <VH : SmartViewHolder<*>> createViewHolder(
            parent: ViewGroup,
            viewType: Int): VH {
        val viewHolder: VH
        smartViewHolderClass = viewTypeMapper.get(viewType)
        constructor = viewHolderConstructorMapper.getConstructor(smartViewHolderClass!!)

        try {
            viewHolder = ReflectionUtils.invokeConstructor(constructor!!,
                    *when(ReflectionUtils.isInnerClass(smartViewHolderClass!!)) {
                        true -> arrayOf<Any?>(null, parent)
                        false -> arrayOf<Any?>(parent)
                    }
            ) as VH
        } catch (e: Exception) {
            throw RuntimeException(String.format("Could not invoke constructor for '%s', '%s'", smartViewHolderClass!!.toString(), e.message), e)
        }

        val smartRecyclerAdapter = smartRecyclerAdapterMapper[viewHolder::class]
        if (viewHolder is SmartAdapterHolder && smartRecyclerAdapter != null) {
            (viewHolder as SmartAdapterHolder).setSmartRecyclerAdapter(smartRecyclerAdapter)
        }

        return viewHolder
    }

    fun addMapping(itemType: KClass<*>, viewHolderType: KClass<out SmartViewHolder<*>>) {
        dataTypeViewHolderMapper[itemType.java.name] = viewHolderType
        viewHolderConstructorMapper.add(viewHolderType)
    }

    fun setDataTypeViewHolderMapper(dataTypeViewHolderMapper: HashMap<String, KClass<out SmartViewHolder<*>>>) {
        this.dataTypeViewHolderMapper = dataTypeViewHolderMapper
        viewHolderConstructorMapper.add(dataTypeViewHolderMapper.values)
    }

    fun setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper: HashMap<KClass<out SmartViewHolder<*>>, SmartRecyclerAdapter>) {
        this.smartRecyclerAdapterMapper = smartRecyclerAdapterMapper
    }
}

fun <E : KClass<out SmartViewHolder<*>>> SparseArray<E>.put(value: E): Int {
    val key = value.java.name.hashCode()
    put(key, value)
    return key
}
