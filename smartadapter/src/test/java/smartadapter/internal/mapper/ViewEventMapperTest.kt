package smartadapter.internal.mapper

import android.view.View
import android.view.ViewGroup
import io.github.manneohlund.smartrecycleradapter.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import smartadapter.Position
import smartadapter.SmartViewHolderType
import smartadapter.ViewEventId
import smartadapter.ViewId
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import smartadapter.viewholders.TestViewHolder

/*
 * Created by Manne Öhlund on 2019-08-27.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewEventMapperTest {

    private var viewEventMapper: ViewEventMapper? = null

    @Before
    fun setUp() {
        viewEventMapper = ViewEventMapper()
    }

    @After
    fun tearDown() {
        viewEventMapper = null
    }

    @Test
    fun testAddValidGenericViewEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }
        val onViewEventListener2 = object : OnViewEventListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }
        val onItemClickListener = object : OnItemClickListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }
        val onItemLongClickListener = object : OnItemLongClickListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }
        val onItemSelectedListener = object : OnItemSelectedListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }

        // When
        viewEventMapper!!.addViewEventListener(onViewEventListener1)
        viewEventMapper!!.addViewEventListener(onViewEventListener2)
        viewEventMapper!!.addViewEventListener(onItemClickListener)
        viewEventMapper!!.addViewEventListener(onItemLongClickListener)
        viewEventMapper!!.addViewEventListener(onItemSelectedListener)

        // Then
        assertEquals(1, viewEventMapper!!.viewEventListenerMap.size.toLong())
        assertTrue(viewEventMapper!!.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertEquals(1, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.size().toLong())
        assertEquals(4, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertTrue(viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined) is OnViewEventListener)
        assertEquals(onViewEventListener2, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined))

        assertTrue(viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_click) is OnItemClickListener)
        assertEquals(onItemClickListener, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_click))

        assertTrue(viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_long_click) is OnItemLongClickListener)
        assertEquals(onItemLongClickListener, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_long_click))

        assertTrue(viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_item_selected) is OnItemSelectedListener)
        assertEquals(onItemSelectedListener, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.event_on_item_selected))
    }

    @Test(expected = RuntimeException::class)
    fun testAddInvalidCustomViewEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener {
            override val viewEventId: ViewEventId
                get() = 123

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }

        // When
        viewEventMapper!!.addViewEventListener(onViewEventListener1)
    }

    @Test
    fun testAddValidCustomViewHolderTypeEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener {

            override val viewHolderType: SmartViewHolderType
                get() = TestViewHolder::class

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }

        // When
        viewEventMapper!!.addViewEventListener(onViewEventListener1)

        // Then
        assertEquals(1, viewEventMapper!!.viewEventListenerMap.size.toLong())
        assertFalse(viewEventMapper!!.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertTrue(viewEventMapper!!.viewEventListenerMap.containsKey(TestViewHolder::class))
        assertEquals(1, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertTrue(viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined) is OnViewEventListener)
        assertEquals(onViewEventListener1, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined))
    }

    @Test
    fun testAddMixedGenericAndCustomViewHolderTypeEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }
        val onViewEventListener2 = object : OnItemClickListener {
            override val viewHolderType = TestViewHolder::class

            override val viewId = 123

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }

        // When
        viewEventMapper!!.addViewEventListener(onViewEventListener1)
        viewEventMapper!!.addViewEventListener(onViewEventListener2)

        // Then
        assertEquals(2, viewEventMapper!!.viewEventListenerMap.size.toLong())
        assertTrue(viewEventMapper!!.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertTrue(viewEventMapper!!.viewEventListenerMap.containsKey(TestViewHolder::class))

        assertEquals(1, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertEquals(1, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(123).size().toLong())
        assertNotNull(viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(R.id.event_on_click))

        assertTrue(viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined) is OnViewEventListener)
        assertEquals(onViewEventListener1, viewEventMapper!!.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(R.id.undefined))

        assertTrue(viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(R.id.event_on_click) is OnItemClickListener)
        assertEquals(onViewEventListener2, viewEventMapper!!.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(R.id.event_on_click))
    }

    @Test
    fun testSmartViewHolder_implViewEventListenerHolder_setOnViewEventListenerIsCalled() {
        open class TestSmartViewEventListenerViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view), ViewEventListenerHolder {

            override lateinit var viewEventListener: OnViewEventListener

            override fun bind(item: Any) {}
        }

        // Given
        val testSmartViewEventListenerViewHolder = spy(TestSmartViewEventListenerViewHolder(mock(ViewGroup::class.java)))
        val onViewEventListener = object : OnViewEventListener {
            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) { }
        }

        // When
        `when`<Any>(testSmartViewEventListenerViewHolder.itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        viewEventMapper!!.addViewEventListener(onViewEventListener)
        viewEventMapper!!.mapViewEventWith(testSmartViewEventListenerViewHolder)

        // Then
        assertEquals(onViewEventListener, testSmartViewEventListenerViewHolder.viewEventListener)
    }

    @Test
    fun testMapViewEventWithSmartViewHolder_withCustomViewId_findViewByIdIsCalled() {
        // Given
        val itemView = mock(ViewGroup::class.java)
        val testViewHolder = spy(TestViewHolder(itemView))
        val onViewEventListener = object : OnViewEventListener {
            override val viewId: ViewId
                get() = 123

            override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {}
        }

        // When
        `when`<Any>(itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        viewEventMapper!!.addViewEventListener(onViewEventListener)
        viewEventMapper!!.mapViewEventWith(testViewHolder)

        // Then
        verify(itemView, times(1)).findViewById<View>(123)
    }
}
