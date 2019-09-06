package smartadapter.internal

import android.view.View
import android.view.ViewGroup

import org.junit.Test

import smartadapter.internal.exception.ConstructorNotFoundException
import smartadapter.internal.utils.ReflectionUtils
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholders.InvalidTestViewHolder
import smartadapter.viewholders.TestViewGroupViewHolder
import smartadapter.viewholders.TestViewHolder

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.mockito.Mockito.mock

/*
 * Created by Manne Öhlund on 2019-06-03.
 * Copyright (c) 2018 All rights reserved.
 */

class ReflectionUtilsTest {

    @Test
    fun testGetConstructor() {
        assertNotNull("Failed to invoke constructor: " + TestViewHolder::class,
                ReflectionUtils.getConstructor(TestViewHolder::class, ViewGroup::class, View::class))

        assertNotNull("Failed to invoke constructor: " + TestViewGroupViewHolder::class,
                ReflectionUtils.getConstructor(TestViewGroupViewHolder::class, View::class, ViewGroup::class))
    }

    @Test
    fun testGetConstructorOnInnerClass() {
        assertNotNull("Failed to invoke constructor: " + StaticInnerClassViewHolder::class,
                ReflectionUtils.getConstructor(StaticInnerClassViewHolder::class, View::class, ViewGroup::class))

        assertNotNull("Failed to invoke constructor: " + InnerClassViewHolder::class,
                ReflectionUtils.getConstructor(InnerClassViewHolder::class, View::class, ViewGroup::class))
    }

    @Test(expected = ConstructorNotFoundException::class)
    fun testGetConstructor_error() {
        ReflectionUtils.getConstructor(InvalidTestViewHolder::class, View::class, ViewGroup::class)
    }

    @Test(expected = ConstructorNotFoundException::class)
    fun testGetConstructorOnInnerClass_error() {
        ReflectionUtils.getConstructor(InvalidInnerClassTestViewHolder::class, View::class, ViewGroup::class)
    }

    @Test(expected = ConstructorNotFoundException::class)
    fun testGetConstructorOnStaticInnerClass_error() {
        ReflectionUtils.getConstructor(InvalidStaticInnerClassTestViewHolder::class, View::class, ViewGroup::class)
    }

    @Test
    @Throws(Exception::class)
    fun invokeConstructorOnInnerClass() {
        // Given
        val classUnderTest = InnerClassViewHolder::class

        // When
        val constructor = ReflectionUtils.getConstructor(classUnderTest, View::class, ViewGroup::class)
        val viewHolder = ReflectionUtils.invokeConstructor(constructor, this, mock(ViewGroup::class.java))

        // Then
        assertNotNull("Constructor is null", constructor)
        assertNotNull("Failed to invoke constructor: $classUnderTest", viewHolder)
    }

    @Test
    @Throws(Exception::class)
    fun invokeConstructorOnStaticInnerClass() {
        // Given
        val classUnderTest = StaticInnerClassViewHolder::class

        // When
        val constructor = ReflectionUtils.getConstructor(classUnderTest, View::class, ViewGroup::class)
        val viewHolder = ReflectionUtils.invokeConstructor(constructor, mock(ViewGroup::class.java))

        // Then
        assertNotNull("Constructor is null", constructor)
        assertNotNull("Failed to invoke constructor: $classUnderTest", viewHolder)
    }

    @Test
    fun testIsStaticCheckOnInnerClass() {
        assertFalse("Class is static", ReflectionUtils.isStatic(InnerClassViewHolder::class.java))
    }

    @Test
    fun testIsStaticCheckOnStaticInnerClass() {
        assertTrue("Class is not static", ReflectionUtils.isStatic(StaticInnerClassViewHolder::class.java))
    }

    @Test
    fun testIsNonStaticInnerClassCheckOnInnerClass() {
        assertTrue("Class is static", ReflectionUtils.isInnerClass(InnerClassViewHolder::class))
    }

    @Test
    fun testIsNonStaticInnerClassCheckOnStaticInnerClass() {
        assertFalse("Class is not static", ReflectionUtils.isInnerClass(StaticInnerClassViewHolder::class))
    }

    /**
     * Test View holders
     */

    inner class InnerClassViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view) {

        override fun bind(item: Any) {

        }
    }

    class StaticInnerClassViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view) {

        override fun bind(item: Any) {

        }
    }

    inner class InvalidInnerClassTestViewHolder(view: View, invalidParam: Int) : SmartViewHolder<Any>(view) {

        override fun bind(item: Any) {

        }
    }

    class InvalidStaticInnerClassTestViewHolder(view: View, invalidParam: Int) : SmartViewHolder<Any>(view) {

        override fun bind(item: Any) {

        }
    }
}