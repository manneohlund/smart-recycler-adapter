package smartadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.`when`
import org.mockito.Mockito.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import smartadapter.extension.SmartRecyclerAdapterBinder
import smartadapter.extension.SmartViewHolderBinder
import smartadapter.listener.OnAttachedToRecyclerViewListener
import smartadapter.listener.OnBindViewHolderListener
import smartadapter.listener.OnCreateViewHolderListener
import smartadapter.listener.OnDetachedFromRecyclerViewListener
import smartadapter.listener.OnViewAttachedToWindowListener
import smartadapter.listener.OnViewDetachedFromWindowListener
import smartadapter.listener.OnViewRecycledListener
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholders.BindableTestViewHolder
import smartadapter.viewholders.RecyclableTestViewHolder
import smartadapter.viewholders.ViewAttachedToWindowTestViewHolder
import java.util.ArrayList

/*
 * Created by Manne Öhlund on 2019-07-02.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
class SmartRecyclerAdapterImplTest {

    @Test
    fun testImplicitInstantiate() {
        SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()
    }

    @Test
    fun testExplicitInstantiate() {
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()
        assertNotNull(smartRecyclerAdapter)
    }

    @Test
    fun testGetExtension() {
        // Given
        class TestExtension(override val identifier: Any) : SmartRecyclerAdapterBinder {
            override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) { }
        }
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.add(TestExtension(identifier = 123))

        // Then
        assertNotNull(smartRecyclerAdapter.get(123))
    }

    @Test
    fun testGetMultipleExtensionsByIdentifier() {
        // Given
        class TestExtension(override val identifier: Any = TestExtension::class) : SmartRecyclerAdapterBinder {
            override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) { }
        }
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.add(TestExtension())
        smartRecyclerAdapter.add(TestExtension(identifier = "abc"))
        smartRecyclerAdapter.add(TestExtension(identifier = 123))

        // Then
        assertNotNull(smartRecyclerAdapter.get<TestExtension>())
        assertNotNull(smartRecyclerAdapter.get("abc"))
        assertNotNull(smartRecyclerAdapter.get(123))
    }

    @Test
    fun testSmartRecyclerAdapterStoresMultipleExtensions_withSameImplicitIdentifier() {
        // Given
        class TestExtension(override val identifier: Any = TestExtension::class) : SmartRecyclerAdapterBinder {
            override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) { }
        }
        class TestViewHolderBinder(override val identifier: Any = TestViewHolderBinder::class) : SmartViewHolderBinder

        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()
        val ext1 = TestExtension()
        val ext2 = TestViewHolderBinder()
        val ext3 = TestExtension()
        val ext4 = TestViewHolderBinder()

        // When
        smartRecyclerAdapter.add(ext1)
        smartRecyclerAdapter.add(ext2)
        smartRecyclerAdapter.add(ext3)
        smartRecyclerAdapter.add(ext4)

        // Then
        assertEquals(4, smartRecyclerAdapter.smartExtensions.size)
        assertNotNull(smartRecyclerAdapter.get<TestExtension>())
        assertNotNull(smartRecyclerAdapter.get<TestViewHolderBinder>())
        assertNotNull(smartRecyclerAdapter.get<TestExtension>(ext3.hashCode()))
        assertNotNull(smartRecyclerAdapter.get<TestViewHolderBinder>(ext4.hashCode()))
        assertTrue(smartRecyclerAdapter.smartExtensions.values.contains(ext1))
        assertTrue(smartRecyclerAdapter.smartExtensions.values.contains(ext2))
        assertTrue(smartRecyclerAdapter.smartExtensions.values.contains(ext3))
        assertTrue(smartRecyclerAdapter.smartExtensions.values.contains(ext4))
    }

    @Test
    fun testSmartRecyclerAdapterExtension_bindingTo_smartRecyclerAdapter() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) : SmartRecyclerAdapterBinder{
            override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) {}
        }
        val testExtension = mock(TestExtension::class.java)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.add(testExtension)

        // Then
        verify(testExtension, times(1)).bind(smartRecyclerAdapter)
    }

    @Test
    fun testSmartViewHolderBinder_onCreateViewHolderListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnCreateViewHolderListener {
            override fun onCreateViewHolder(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))

        // Then
        verify(testExtension, times(1)).onCreateViewHolder(smartRecyclerAdapter, viewHolder)
    }

    @Test
    fun testSmartViewHolderBinder_onBindViewHolderListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnBindViewHolderListener {
            override fun onBindViewHolder(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        smartRecyclerAdapter.onBindViewHolder(viewHolder, 0)

        // Then
        verify(testExtension, times(1)).onBindViewHolder(smartRecyclerAdapter, viewHolder)
    }

    @Test
    fun testSmartViewHolderBinder_onBindViewHolderListener2() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnBindViewHolderListener {
            override fun onBindViewHolder(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {}
            override fun onBindViewHolder(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>, payloads: MutableList<Any>) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        val payload = mutableListOf<Any>(123)
        smartRecyclerAdapter.onBindViewHolder(viewHolder, 0, payload)

        // Then
        verify(testExtension, times(1)).onBindViewHolder(smartRecyclerAdapter, viewHolder, payload)
    }

    @Test
    fun testSmartViewHolderBinder_onViewRecycledListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnViewRecycledListener {
            override fun onViewRecycled(adapter: SmartRecyclerAdapter, viewHolder: SmartViewHolder<Any>) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        smartRecyclerAdapter.onViewRecycled(viewHolder)

        // Then
        verify(testExtension, times(1)).onViewRecycled(smartRecyclerAdapter, viewHolder)
    }

    @Test
    fun testSmartViewHolderBinder_onViewAttachedToWindowListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnViewAttachedToWindowListener {
            override fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        smartRecyclerAdapter.onViewAttachedToWindow(viewHolder)

        // Then
        verify(testExtension, times(1)).onViewAttachedToWindow(viewHolder)
    }

    @Test
    fun testSmartViewHolderBinder_onViewDetachedFromWindowListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnViewDetachedFromWindowListener {
            override fun onViewDetachedFromWindow(viewHolder: RecyclerView.ViewHolder) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        smartRecyclerAdapter.onViewDetachedFromWindow(viewHolder)

        // Then
        verify(testExtension, times(1)).onViewDetachedFromWindow(viewHolder)
    }

    @Test
    fun testSmartViewHolderBinder_onAttachedToRecyclerViewListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnAttachedToRecyclerViewListener {
            override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        val recyclerView = mock(RecyclerView::class.java)
        smartRecyclerAdapter.onAttachedToRecyclerView(recyclerView)

        // Then
        verify(testExtension, times(1)).onAttachedToRecyclerView(recyclerView)
    }

    @Test
    fun testSmartViewHolderBinder_onDetachedFromRecyclerViewListener() {
        // Given
        open class TestExtension(override val identifier: Any = TestExtension::class) :
            SmartViewHolderBinder,
            OnDetachedFromRecyclerViewListener {
            override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {}
        }
        open class TestViewHolder(viewGroup: ViewGroup) : SmartViewHolder<Int>(viewGroup) {
            override fun bind(item: Int) {}
        }

        val testExtension = mock(TestExtension::class.java)
        `when`(testExtension.viewHolderType).thenReturn(SmartViewHolder::class)
        val smartRecyclerAdapter = SmartRecyclerAdapter.empty().create<SmartRecyclerAdapter>()

        // When
        smartRecyclerAdapter.setItems(mutableListOf(123))
        smartRecyclerAdapter.add(testExtension)
        smartRecyclerAdapter.map(Int::class, TestViewHolder::class)
        val viewHolder = smartRecyclerAdapter.onCreateViewHolder(mock(ViewGroup::class.java), smartRecyclerAdapter.getItemViewType(0))
        val recyclerView = mock(RecyclerView::class.java)
        smartRecyclerAdapter.onDetachedFromRecyclerView(recyclerView)

        // Then
        verify(testExtension, times(1)).onDetachedFromRecyclerView(recyclerView)
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
        val items = listOf("Test", "Test2", 1, 2, 3, "test3")

        // When
        val adapter = SmartRecyclerAdapter.items(items).create<SmartRecyclerAdapter>()

        // Then
        assertEquals(3, adapter.getItemCount(String::class))
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