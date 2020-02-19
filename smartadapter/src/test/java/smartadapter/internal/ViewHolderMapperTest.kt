package smartadapter.internal

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.validateMockitoUsage
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import smartadapter.SmartRecyclerAdapter
import smartadapter.internal.extension.name
import smartadapter.internal.mapper.ViewHolderMapper
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholders.JavaTestViewHolder
import smartadapter.widget.ViewTypeResolver

/*
 * Created by Manne Öhlund on 2019-07-19.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewHolderMapperTest {

    private lateinit var mapper: ViewHolderMapper

    @Before
    fun setUp() {
        mapper = ViewHolderMapper()
        mapper.smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)
        mapper.addMapping(Float::class, JavaTestViewHolder::class)
        mapper.addMapping(String::class, TestViewHolder::class)
        mapper.addMapping(Int::class, TestViewHolder2::class)
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }

    @Test
    fun getItemViewTypeResolver() {
        // Given
        val viewTypeResolver: ViewTypeResolver = { item, _ ->
            when (item) {
                is Double -> TestViewHolder3::class
                is Float -> TestViewHolder4::class
                is Int -> TestViewHolder2::class
                else -> null
            }
        }

        // When
        val id = mapper.getItemViewType(viewTypeResolver, "Hello", 0)
        val id2 = mapper.getItemViewType(viewTypeResolver, "Hello2", 1)
        val id3 = mapper.getItemViewType(viewTypeResolver, 1, 2)
        val id4 = mapper.getItemViewType(viewTypeResolver, 2.2, 3)
        val id5 = mapper.getItemViewType(viewTypeResolver, 3.3f, 4)

        // Then
        assertEquals(TestViewHolder::class, mapper.createViewHolder<SmartViewHolder<Any>>(mock(ViewGroup::class.java), id)::class)
        assertEquals(TestViewHolder::class, mapper.createViewHolder<SmartViewHolder<Any>>(mock(ViewGroup::class.java), id2)::class)
        assertEquals(TestViewHolder2::class, mapper.createViewHolder<SmartViewHolder<Any>>(mock(ViewGroup::class.java), id3)::class)
        assertEquals(TestViewHolder3::class, mapper.createViewHolder<SmartViewHolder<Any>>(mock(ViewGroup::class.java), id4)::class)
        assertEquals(TestViewHolder4::class, mapper.createViewHolder<SmartViewHolder<Any>>(mock(ViewGroup::class.java), id5)::class)

        assertEquals(TestViewHolder::class.name.hashCode(), id)
        assertEquals(TestViewHolder::class.name.hashCode(), id2)
        assertEquals(TestViewHolder2::class.name.hashCode(), id3)
        assertEquals(TestViewHolder3::class.name.hashCode(), id4)
        assertEquals(TestViewHolder4::class.name.hashCode(), id5)
    }

    @Test(expected = RuntimeException::class)
    fun getItemViewType_error() {
        // When
        mapper.getItemViewType(null, 1.1, 0)
    }

    @Test
    fun getItemViewType() {
        val viewTypeResolver: ViewTypeResolver = { item, _ ->
            when (item) {
                is Int -> TestViewHolder2::class
                else -> null // No type, fallback on mapping
            }
        }

        // When
        val id = mapper.getItemViewType(null, "Hello", 0)
        val id2 = mapper.getItemViewType(null, "Hello2", 1)
        val id3 = mapper.getItemViewType(viewTypeResolver, 2, 2)

        // Then
        assertEquals(TestViewHolder::class.name.hashCode(), id)
        assertEquals(TestViewHolder::class.name.hashCode(), id2)
        assertEquals(TestViewHolder2::class.name.hashCode(), id3)
    }

    @Test
    fun createViewHolder() {
        // When
        mapper.getItemViewType(null, "Hello", 0)
        val viewHolder = mapper.createViewHolder<SmartViewHolder<Any>>(mock(RecyclerView::class.java), TestViewHolder::class.name.hashCode())

        // Then
        assertTrue(viewHolder is TestViewHolder)
    }

    @Test
    fun createJavaViewHolder() {
        // Given
        mapper.addMapping(Float::class.java.kotlin, JavaTestViewHolder::class)

        // When
        val viewType = mapper.getItemViewType(null, 3.3f, 0)
        val viewHolder = mapper.createViewHolder<SmartViewHolder<Any>>(mock(RecyclerView::class.java), viewType)

        // Then
        assertTrue(viewHolder is JavaTestViewHolder)
    }

    inner class TestViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view) {
        override fun bind(item: Any) { }
    }

    inner class TestViewHolder2(view: ViewGroup) : SmartViewHolder<Any>(view) {
        override fun bind(item: Any) { }
    }

    inner class TestViewHolder3(view: ViewGroup) : SmartViewHolder<Any>(view) {
        override fun bind(item: Any) { }
    }

    inner class TestViewHolder4(view: ViewGroup) : SmartViewHolder<Any>(view) {
        override fun bind(item: Any) { }
    }
}
