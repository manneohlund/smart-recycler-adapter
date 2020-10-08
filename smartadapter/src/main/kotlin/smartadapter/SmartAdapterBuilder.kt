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
import smartadapter.internal.extension.isMutable
import smartadapter.internal.extension.name
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.widget.ViewTypeResolver
import java.util.HashMap
import kotlin.reflect.KClass

/**
 * Builder for [SmartRecyclerAdapter].
 */
open class SmartAdapterBuilder {
    internal var items: MutableList<Any> = mutableListOf()
    internal var layoutManager: RecyclerView.LayoutManager? = null
    internal var viewTypeResolver: ViewTypeResolver? = null
    internal val viewHolderMapper = HashMap<String, SmartViewHolderType>()
    internal val viewHolderBinders = mutableListOf<SmartViewHolderBinder>()
    internal val smartRecyclerAdapterExtensions = mutableMapOf<Any, SmartRecyclerAdapterExtension>()

    internal open fun getSmartRecyclerAdapter() = SmartRecyclerAdapter(items.let {
        (if (it.isMutable()) it else it.toMutableList())
    })

    fun map(itemType: KClass<*>, viewHolderType: SmartViewHolderType): SmartAdapterBuilder {
        viewHolderMapper[itemType.name] = viewHolderType
        return this
    }

    fun setItems(items: List<Any>): SmartAdapterBuilder {
        this.items = (if (items.isMutable()) items else items.toMutableList()) as MutableList<Any>
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
        val smartRecyclerAdapter = getSmartRecyclerAdapter()
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
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
        val smartRecyclerAdapter = getSmartRecyclerAdapter()
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
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
