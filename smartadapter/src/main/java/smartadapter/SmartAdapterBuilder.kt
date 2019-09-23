package smartadapter

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import smartadapter.internal.factory.SmartRecyclerAdapterExtensionFactory
import smartadapter.internal.mapper.ViewEventMapper
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnViewEventListener
import smartadapter.widget.ViewTypeResolver
import java.util.*
import kotlin.reflect.KClass

/**
 * Builder for [SmartRecyclerAdapter].
 */
class SmartAdapterBuilder internal constructor(private val smartRecyclerAdapter: SmartRecyclerAdapter) {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var viewTypeResolver: ViewTypeResolver? = null
    private val viewHolderMapper = HashMap<String, SmartViewHolderType>()
    private val smartRecyclerAdapterMapper = HashMap<SmartViewHolderType, SmartRecyclerAdapter>()
    private val viewEventMapper = ViewEventMapper()
    private val smartRecyclerAdapterExtensionFactory = SmartRecyclerAdapterExtensionFactory()

    fun map(itemType: KClass<*>, viewHolderType: SmartViewHolderType): SmartAdapterBuilder {
        viewHolderMapper[itemType.java.name] = viewHolderType
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
     * Adds [OnViewEventListener] to the [SmartRecyclerAdapter].
     * The adapter will then automatically map the [OnViewEventListener] to the target view holder class with [OnViewEventListener.viewHolderType],
     * set the viewEventListener on the right View with viewId using [OnViewEventListener.viewId].
     *
     * @see OnViewEventListener.viewEventId
     * @param viewEventListener target [OnViewEventListener]
     * @return SmartAdapterBuilder
     */
    fun addViewEventListener(viewEventListener: OnViewEventListener): SmartAdapterBuilder {
        if (viewEventListener is OnItemSelectedListener) {
            viewEventListener.selectionStateHolder.smartRecyclerAdapter = smartRecyclerAdapter
            viewEventMapper.addViewEventListener(viewEventListener)
        } else {
            viewEventMapper.addViewEventListener(viewEventListener)
        }
        return this
    }

    /**
     * Adds [SmartExtensionBuilder] to [SmartRecyclerAdapterExtensionFactory] that will build and bind
     * the [SmartRecyclerAdapter] and [RecyclerView] to the ExtensionBuilder.
     *
     * @param smartExtensionBuilder extension builder
     * @return SmartAdapterBuilder
     */
    fun addExtensionBuilder(smartExtensionBuilder: SmartExtensionBuilder<*>): SmartAdapterBuilder {
        smartRecyclerAdapterExtensionFactory.add(smartExtensionBuilder)
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> into(recyclerView: RecyclerView): T {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper)
        smartRecyclerAdapter.viewTypeResolver = viewTypeResolver
        smartRecyclerAdapter.viewEventMapper = viewEventMapper
        recyclerView.adapter = smartRecyclerAdapter
        recyclerView.layoutManager = getLayoutManager(recyclerView.context)
        smartRecyclerAdapterExtensionFactory.build(smartRecyclerAdapter, recyclerView)
        return smartRecyclerAdapter as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> create(): T {
        smartRecyclerAdapter.setDataTypeViewHolderMapper(viewHolderMapper)
        smartRecyclerAdapter.setSmartRecyclerAdapterMapper(smartRecyclerAdapterMapper)
        smartRecyclerAdapter.viewTypeResolver = viewTypeResolver
        smartRecyclerAdapter.viewEventMapper = viewEventMapper
        return smartRecyclerAdapter as T
    }
}
