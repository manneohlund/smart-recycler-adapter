package smartadapter.internal

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import smartadapter.internal.mapper.ViewHolderConstructorMapper
import smartadapter.viewholder.SmartViewHolder
import kotlin.reflect.KClass

/*
 * Created by Manne Öhlund on 2019-07-19.
 * Copyright (c) All rights reserved.
 */

class ViewHolderConstructorMapperTest {

    @Test
    fun testViewHolderConstructorMapper() {
        // Given
        val list: MutableList<KClass<out SmartViewHolder<Any>>> = mutableListOf(
                ReflectionUtilsTest.InnerClassViewHolder::class,
                ReflectionUtilsTest.StaticInnerClassViewHolder::class)
        val viewHolderConstructorMapper = ViewHolderConstructorMapper()

        // When
        viewHolderConstructorMapper.add(list)
        viewHolderConstructorMapper.add(ReflectionUtilsTest.InnerClassViewHolder::class)

        // Then
        assertNotNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.InnerClassViewHolder::class))
        assertNotNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.StaticInnerClassViewHolder::class))
        assertNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.InvalidInnerClassTestViewHolder::class))
    }
}