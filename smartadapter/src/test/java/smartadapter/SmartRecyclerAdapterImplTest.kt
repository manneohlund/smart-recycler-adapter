package smartadapter

import android.view.ViewGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import smartadapter.viewholders.BindableTestViewHolder
import smartadapter.viewholders.RecyclableTestViewHolder
import smartadapter.viewholders.ViewAttachedToWindowTestViewHolder
import java.util.*

/*
 * Created by Manne Öhlund on 2019-07-02.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
class SmartRecyclerAdapterImplTest {

    @Test
    fun testImplicitInstantiate() {
        SmartRecyclerAdapter.empty().create<Any>()
    }

    @Test
    fun testExplicitInstantiate() {
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()
        assertNotNull(smartRecyclerAdapter)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_addItem() {
        // Given
        val items = mock(ArrayList::class.java) as ArrayList<Any>
        val testDataItem = "Test"
        val adapter = spy(SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>())

        // When
        adapter.addItem(testDataItem)

        // Then
        verify<ArrayList<Any>>(items).add(testDataItem)
        verify(items).add(testDataItem)
        verify(adapter, times(1)).addItem(testDataItem)
        verify(adapter, times(1)).addItem(testDataItem, true)
        verify(adapter, times(1)).smartNotifyDataSetChanged()
        verify(adapter, times(1)).updateItemCount()
        verify(adapter, times(1)).notifyDataSetChanged()
    }

    @Test
    fun testSmartRecyclerAdapterDataList_addItem_notifyDataSetChanged() {
        // Given
        val items = mock(ArrayList::class.java) as ArrayList<Any>
        val testDataItem = "Test"
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // When
        adapter.addItem(testDataItem, false)

        // Then
        verify<ArrayList<Any>>(items).add(testDataItem)
        verify(items).add(testDataItem)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_addItemAtIndex() {
        // Given
        val items = mock(ArrayList::class.java) as ArrayList<Any>
        val testDataItem = "Test"
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // When
        adapter.addItem(0, testDataItem, false)

        // Then
        verify(items).add(0, testDataItem)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_addItems() {
        // Given
        val items = mock(ArrayList::class.java) as ArrayList<Any>
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // When
        adapter.addItems(items, false)

        // Then
        verify(items).addAll(items)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_addItemsAtIndex() {
        // Given
        val items = mock(ArrayList::class.java) as ArrayList<Any>
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // When
        adapter.addItems(0, items, false)

        // Then
        verify(items).addAll(0, items)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_setItems_notifyDataSetChanged() {
        // Given
        val items = mutableListOf("Test")
        val adapter = SmartRecyclerAdapter
            .items(mock(ArrayList::class.java) as ArrayList<Any>)
            .create<SmartRecyclerAdapter>()

        // When
        adapter.setItems(items, false)

        // Then
        assertEquals(items, adapter.getItems())
    }

    @Test
    fun testSmartRecyclerAdapterDataList_setItems() {
        // Given
        val items = spy(mutableListOf("Test"))
        val adapter = spy(SmartRecyclerAdapter
            .items(mock(ArrayList::class.java) as ArrayList<Any>)
            .create<SmartRecyclerAdapter>())

        // When
        adapter.setItems(items)

        // Then
        assertEquals(items, adapter.getItems())
        verify(adapter, times(1)).setItems(items, true)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_getItemAtIndex() {
        // Given
        val testItem = "Test"
        val testItem2 = "Test2"
        val items = listOf(testItem, testItem2)

        // When
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(testItem2, adapter.getItem(1))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_getItems() {
        // Given
        val items = listOf("Test")

        // When
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(items, adapter.getItems())
    }

    @Test
    fun testSmartRecyclerAdapterDataList_getItemsOfType() {
        // Given
        val items = listOf("Test", "Test2", 1, 2, 3)

        // When
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(2, adapter.getItems(String::class).size)
        assertEquals(3, adapter.getItems(Int::class).size)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_getItemCount() {
        // Given
        val items = listOf("Test", "Test2", 1, 2, 3)

        // When
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(2, adapter.getItemCount(String::class))
        assertEquals(3, adapter.getItemCount(Int::class))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_removeItem() {
        val items = listOf("Test")
        val adapter = spy(SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>())

        // Then
        assertEquals(adapter.itemCount, 1)

        // When
        adapter.removeItem(0)

        // Then
        assertEquals(0, adapter.itemCount)
        verify(adapter, times(1)).removeItem(eq(0), eq(true))
        verify(adapter, times(1)).smartNotifyItemRemoved(eq(0))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_removeItem_emptyAdapterList() {
        // Given
        val adapter = spy(SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>())

        // When
        val result = adapter.removeItem(0)

        // Then
        assertEquals(0, adapter.itemCount)
        assertEquals(false, result)
        verify(adapter, times(1)).removeItem(eq(0), eq(true))
        verify(adapter, times(0)).smartNotifyItemRemoved(eq(0))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_removeItem_notifyDataSetChanged() {
        val items = listOf("Test")
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(adapter.itemCount, 1)

        // When
        adapter.removeItem(0, false)
        adapter.updateItemCount() // Needed because of notifyDataSetChanged param is false

        // Then
        assertEquals(adapter.itemCount, 0)
    }

    @Test
    fun testSmartRecyclerAdapterDataList_replaceItem() {
        // Given
        val items = listOf("Test", "Test2")
        val replaceItem = "Test2"

        // When
        val adapter = spy(SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>())
        adapter.replaceItem(0, replaceItem, false)

        // Then
        assertEquals(adapter.getItems()[0], replaceItem)
        verify(adapter, times(0)).smartNotifyItemChanged(any(Int::class.java))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_replaceItem_smartNotifyItemChanged() {
        // Given
        val items = listOf("Test", "Test2")
        val replaceItem = "Test2"

        // When
        val adapter = spy(SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>())
        adapter.replaceItem(0, replaceItem)

        // Then
        assertEquals(adapter.getItems()[0], replaceItem)
        verify(adapter, times(1)).smartNotifyItemChanged(any(Int::class.java))
    }

    @Test
    fun testSmartRecyclerAdapterDataList_clear() {
        // Given
        val items = listOf("Test", "Test2")

        // When
        val adapter = spy(SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>())
        adapter.clear()

        // Then
        assertEquals(0, adapter.itemCount)
        verify(adapter, times(1)).clear()
        verify(adapter, times(1)).smartNotifyDataSetChanged()
        verify(adapter, times(1)).notifyDataSetChanged()
        verify(adapter, times(1)).updateItemCount()
    }

    @Test
    fun testRecyclableViewHolder() {
        // Given
        val viewHolder = mock(RecyclableTestViewHolder::class.java)
        val adapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        adapter.onFailedToRecycleView(viewHolder)

        // Then
        verify(viewHolder, times(1)).onFailedToRecycleView()
    }

    @Test
    fun testBindableViewHolder() {
        // Given
        val items = ArrayList<Any>()
        items.add("Test")
        val viewHolder = spy(BindableTestViewHolder(mock(ViewGroup::class.java)))
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // When
        adapter.onBindViewHolder(viewHolder, 0)
        adapter.onViewRecycled(viewHolder)

        // Then
        verify(viewHolder, times(1)).bind(items[0])
        verify(viewHolder, times(1)).unbind()
    }

    @Test
    fun testRecyclableViewHolder2() {
        // Given
        val viewHolder = spy(ViewAttachedToWindowTestViewHolder(mock(ViewGroup::class.java)))
        val adapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        adapter.onViewAttachedToWindow(viewHolder)
        adapter.onViewDetachedFromWindow(viewHolder)

        // Then
        verify(
            viewHolder,
            times(1)
        ).onViewAttachedToWindow(viewHolder)
        verify(
            viewHolder,
            times(1)
        ).onViewDetachedFromWindow(viewHolder)
    }
}