package smartadapter

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import smartadapter.binders.ItemTouchBinder
import smartadapter.binders.SmartRecyclerAdapterExtension
import smartadapter.internal.extension.name
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.widget.ViewTypeResolver
import java.util.HashMap
import kotlin.reflect.KClass

/**
 * Builder for [SmartRecyclerAdapter].
 */
class SmartAdapterBuilder internal constructor(private val smartRecyclerAdapter: SmartRecyclerAdapter) {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var viewTypeResolver: ViewTypeResolver? = null
    private val viewHolderMapper = HashMap<String, SmartViewHolderType>()
    private val smartRecyclerAdapterMapper = HashMap<SmartViewHolderType, SmartRecyclerAdapter>()
    private val viewHolderBinders = mutableListOf<SmartViewHolderBinder>()
    private val smartRecyclerAdapterExtensions = mutableMapOf<Any, SmartRecyclerAdapterExtension>()

    fun map(itemType: KClass<*>, viewHolderType: SmartViewHolderType): SmartAdapterBuilder {
        viewHolderMapper[itemType.name] = viewHolderType
        return this
    }

    fun map(viewHolderType: SmartViewHolderType, smartRecyclerAdapter: SmartRecyclerAdapter): SmartAdapterBuilder {
        smartRecyclerAdapterMapper[viewHolderType] = smartRecyclerAdapter
        return this
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager): SmartAdapterBuilder {
        this.layoutManager = layoutManager
        return this
    }

    private fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context)
        }
        return layoutManager!!
    }

    fun setViewTypeResolver(viewTypeResolver: ViewTypeResolver): SmartAdapterBuilder {
        this.viewTypeResolver = viewTypeResolver
        return this
    }

    /**
     * Adds [SmartViewHolderBinder] to the adapter.
     *
     * @param viewHolderBinder extension view holder binder
     * @return SmartAdapterBuilder
     */
    fun addBinder(viewHolderBinder: SmartViewHolderBinder): SmartAdapterBuilder {
        viewHolderBinders.add(viewHolderBinder)
        return this
    }

    /**
     * Adds [SmartRecyclerAdapterExtension] to the adapter with [SmartRecyclerAdapterExtension.identifier] as key.
     *
     * @param extension extension
     * @return SmartAdapterBuilder
     */
    fun addExtension(extension: SmartRecyclerAdapterExtension): SmartAdapterBuilder {
        smartRecyclerAdapterExtensions[extension.identifier] = extension
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> into(recyclerView: RecyclerView): T {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper)
        smartRecyclerAdapter.viewTypeResolver = viewTypeResolver
        recyclerView.adapter = smartRecyclerAdapter
        recyclerView.layoutManager = getLayoutManager(recyclerView.context)
        viewHolderBinders.forEach {
            smartRecyclerAdapter.addBinder(it)
            (it as? SmartAdapterHolder)?.smartRecyclerAdapter = smartRecyclerAdapter
            (it as? ItemTouchBinder<*>)?.bind(smartRecyclerAdapter, recyclerView)
        }
        smartRecyclerAdapterExtensions.forEach {
            smartRecyclerAdapter.addExtension(it.value)
        }
        return smartRecyclerAdapter as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> create(): T {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper)
        smartRecyclerAdapter.viewTypeResolver = viewTypeResolver
        viewHolderBinders.forEach {
            smartRecyclerAdapter.addBinder(it)
            (it as? SmartAdapterHolder)?.smartRecyclerAdapter = smartRecyclerAdapter
        }
        smartRecyclerAdapterExtensions.forEach {
            smartRecyclerAdapter.addExtension(it.value)
        }
        return smartRecyclerAdapter as T
    }
}
