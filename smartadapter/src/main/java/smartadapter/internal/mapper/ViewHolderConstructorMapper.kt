package smartadapter.internal.mapper

/*
 * Created by Manne Öhlund on 2019-07-16.
 * Copyright (c) All rights reserved.
 */

import android.view.View
import android.view.ViewGroup
import smartadapter.internal.utils.ReflectionUtils
import smartadapter.viewholder.SmartViewHolder
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class ViewHolderConstructorMapper {

    private val viewHolderConstructorMapper = HashMap<KClass<*>, KFunction<Any>>()

    fun add(smartViewHolderClasses: Collection<KClass<out SmartViewHolder<*>>>) {
        for (smartViewHolderClass in smartViewHolderClasses) {
            add(smartViewHolderClass)
        }
    }

    fun add(smartViewHolderClass: KClass<out SmartViewHolder<*>>) {
        if (!viewHolderConstructorMapper.containsKey(smartViewHolderClass)) {
            viewHolderConstructorMapper[smartViewHolderClass] = ReflectionUtils.getConstructor(smartViewHolderClass, View::class, ViewGroup::class)
        }
    }

    fun getConstructor(smartViewHolderClass: KClass<out SmartViewHolder<*>>): KFunction<Any>? {
        return viewHolderConstructorMapper[smartViewHolderClass]
    }
}
