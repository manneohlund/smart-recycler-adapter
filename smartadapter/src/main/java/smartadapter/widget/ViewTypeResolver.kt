package smartadapter.widget

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import smartadapter.viewholder.SmartViewHolder
import kotlin.reflect.KClass

interface ViewTypeResolver {
    fun getViewType(item: Any, position: Int): KClass<out SmartViewHolder<*>>?
}