package smartadapter.internal.mapper

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import io.github.manneohlund.smartrecycleradapter.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartadapter.ViewEventId
import smartadapter.ViewId
import smartadapter.listener.OnClick
import smartadapter.listener.OnItemClickListener
import smartadapter.listener.OnItemLongClickListener
import smartadapter.listener.OnItemSelectedListener
import smartadapter.listener.OnLongClick
import smartadapter.listener.OnSmartViewEvent
import smartadapter.listener.OnViewEvent
import smartadapter.listener.OnViewEventListener
import smartadapter.listener.ViewEvent
import smartadapter.viewholder.SmartAdapterHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder
import smartadapter.viewholders.TestViewHolder

/*
 * Created by Manne Öhlund on 2019-08-27.
 * Copyright (c) All rights reserved.
 */

internal inline class OnCustomViewEvent(
    override val event: (View, ViewEventId, SmartRecyclerAdapter, Position, Bundle) -> Unit
) : ViewEvent

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewEventMapperTest {

    private lateinit var viewEventMapper: ViewEventMapper

    @Before
    fun setUp() {
        viewEventMapper = ViewEventMapper()
        viewEventMapper.smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)
    }

    @After
    fun tearDown() {
        viewEventMapper = ViewEventMapper()
    }

    @Test
    fun testAddValidGenericViewEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent { view, viewEventId, pos -> }
        }
        val onViewEventListener2 = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent { view, viewEventId, pos -> }
        }
        val onItemClickListener = object : OnItemClickListener {
            override val listener: OnClick = { view, adapter, pos ->

            }
        }
        val onItemLongClickListener = object : OnItemLongClickListener {
            override val listener: OnLongClick = { view, smartRecyclerAdapter, pos ->  }
        }
        val onItemSelectedListener = object : OnItemSelectedListener {
            override val listener: OnClick = { view, smartRecyclerAdapter, i ->  }
        }

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1)
        viewEventMapper.addViewEventListener(onViewEventListener2)
        viewEventMapper.addViewEventListener(onItemClickListener)
        viewEventMapper.addViewEventListener(onItemLongClickListener)
        viewEventMapper.addViewEventListener(onItemSelectedListener)

        // Then
        assertEquals(1, viewEventMapper.viewEventListenerMap.size.toLong())
        assertTrue(viewEventMapper.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertEquals(1, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.size().toLong())
        assertEquals(5, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertTrue(viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener1.hashCode()) is OnViewEventListener)
        assertEquals(onViewEventListener2, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener2.hashCode()))

        assertTrue(viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemClickListener.hashCode()) is OnItemClickListener)
        assertEquals(onItemClickListener, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemClickListener.hashCode()))

        assertTrue(viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemLongClickListener.hashCode()) is OnItemLongClickListener)
        assertEquals(onItemLongClickListener, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemLongClickListener.hashCode()))

        assertTrue(viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemSelectedListener.hashCode()) is OnItemSelectedListener)
        assertEquals(onItemSelectedListener, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onItemSelectedListener.hashCode()))
    }

    @Test
    fun testAddValidCustomViewHolderTypeEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener<OnViewEvent> {
            override val viewHolderType = TestViewHolder::class
            override val listener = OnViewEvent { _, _, _ ->  }
        }

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1)

        // Then
        assertEquals(1, viewEventMapper.viewEventListenerMap.size.toLong())
        assertFalse(viewEventMapper.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertTrue(viewEventMapper.viewEventListenerMap.containsKey(TestViewHolder::class))
        assertEquals(1, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertTrue(viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener1.hashCode()) is OnViewEventListener)
        assertEquals(onViewEventListener1, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener1.hashCode()))
    }

    @Test
    fun testAddMixedGenericAndCustomViewHolderTypeEventListener() {
        // Given
        val onViewEventListener1 = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent{ _, _, _ ->  }
        }

        val onViewEventListener2 = object : OnViewEventListener<OnViewEvent> {
            override val viewHolderType = TestViewHolder::class
            override val viewId = 123
            override val listener = OnViewEvent{ _, _, _ ->  }
        }

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1)
        viewEventMapper.addViewEventListener(onViewEventListener2)

        // Then
        assertEquals(2, viewEventMapper.viewEventListenerMap.size.toLong())
        assertTrue(viewEventMapper.viewEventListenerMap.containsKey(SmartViewHolder::class))
        assertTrue(viewEventMapper.viewEventListenerMap.containsKey(TestViewHolder::class))

        assertEquals(1, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).size().toLong())

        assertEquals(1, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.size().toLong())
        assertEquals(1, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(123).size().toLong())
        assertNotNull(viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(onViewEventListener2.hashCode()))

        assertTrue(viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener1.hashCode()) is OnViewEventListener)
        assertEquals(onViewEventListener1, viewEventMapper.viewEventListenerMap[SmartViewHolder::class]!!.get(R.id.undefined).get(onViewEventListener1.hashCode()))

        assertTrue(viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(onViewEventListener2.hashCode()) is OnViewEventListener)
        assertEquals(onViewEventListener2, viewEventMapper.viewEventListenerMap[TestViewHolder::class]!!.get(123).get(onViewEventListener2.hashCode()))
    }

    @Test
    fun testSmartViewHolder_implViewEventListenerHolder_setOnViewEventListenerIsCalled() {
        val view = mock(View::class.java)

        open class TestSmartViewEventListenerViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view),
            ViewEventListenerHolder<OnViewEvent> {

            override var viewEventListener: OnViewEvent? = null
                set(value) {
                    if (value is OnViewEvent)
                        field = value
                }

            override fun bind(item: Any) {
                viewEventListener!!.event.invoke(view, 123, 0)
            }
        }

        // Given
        val testSmartViewEventListenerViewHolder = spy(TestSmartViewEventListenerViewHolder(mock(ViewGroup::class.java)))
        val onViewEventListener = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent { v, e, p ->
                assertEquals(view, v)
                assertEquals(123, e)
                assertEquals(0, p)
            }
        }
        val onViewEventListener2 = object : OnViewEventListener<OnClick> {
            override val listener: OnClick = { view, smartRecyclerAdapter, i -> }
        }

        // When
        assertTrue(onViewEventListener.listener is ViewEvent)
        `when`<Any>(testSmartViewEventListenerViewHolder.itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        viewEventMapper.addViewEventListener(onViewEventListener)
        viewEventMapper.addViewEventListener(onViewEventListener2)
        viewEventMapper.mapViewEventWith(testSmartViewEventListenerViewHolder)
        testSmartViewEventListenerViewHolder.bind(mock(Any::class.java))

        // Then
        assertEquals(onViewEventListener.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertNotEquals(onViewEventListener2.listener, testSmartViewEventListenerViewHolder.viewEventListener)
    }

    @Test
    fun testSmartViewHolder_implViewEventListenerHolder_setOnViewEventListenerOnClickIsCalled() {
        val view = mock(View::class.java)
        val smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)

        open class TestSmartViewEventListenerViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view),
            ViewEventListenerHolder<ViewEvent> {

            override var viewEventListener: ViewEvent? = null
                set(value) {
                    if (value is OnSmartViewEvent)
                        field = value
                }

            override fun bind(item: Any) {
                (viewEventListener!! as OnSmartViewEvent).event.invoke(view, 123, smartRecyclerAdapter, 0)
            }
        }

        // Given
        val testSmartViewEventListenerViewHolder = spy(TestSmartViewEventListenerViewHolder(mock(ViewGroup::class.java)))
        val onViewEventListener = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent { v, e, p ->
                assertEquals(view, v)
                assertEquals(123, e)
                assertEquals(0, p)
            }
        }
        val onViewEventListener2 = object : OnViewEventListener<OnClick> {
            override val listener: OnClick = { view, smartRecyclerAdapter, i ->

            }
        }
        val onViewEventListener3 = object : OnViewEventListener<OnSmartViewEvent> {
            override val listener: OnSmartViewEvent = OnSmartViewEvent { v, e, s, p ->
                assertEquals(view, v)
                assertEquals(123, e)
                assertEquals(smartRecyclerAdapter, s)
                assertEquals(0, p)
            }
        }

        // When
        `when`<Any>(testSmartViewEventListenerViewHolder.itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        viewEventMapper.addViewEventListener(onViewEventListener)
        viewEventMapper.addViewEventListener(onViewEventListener2)
        viewEventMapper.addViewEventListener(onViewEventListener3)
        viewEventMapper.mapViewEventWith(testSmartViewEventListenerViewHolder)
        testSmartViewEventListenerViewHolder.bind(mock(Any::class.java))

        // Then
        assertNotEquals(onViewEventListener.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertNotEquals(onViewEventListener2.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertEquals(onViewEventListener3.listener, testSmartViewEventListenerViewHolder.viewEventListener)
    }

    @Test
    fun testSmartViewHolder_implViewEventListenerHolder_setCustomOnViewEventListener_OnClickIsCalled() {
        val viewMock = mock(View::class.java)
        val bundleMock = mock(Bundle::class.java)
        val smartRecyclerAdapterMock = mock(SmartRecyclerAdapter::class.java)

        open class TestSmartViewEventListenerViewHolder(view: ViewGroup) : SmartViewHolder<Any>(view),
            SmartAdapterHolder,
            ViewEventListenerHolder<ViewEvent> {

            override lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

            override var viewEventListener: ViewEvent? = null
                set(value) {
                    if (value is OnCustomViewEvent)
                        field = value
                }

            override fun bind(item: Any) {
                (viewEventListener!! as OnCustomViewEvent).event.invoke(viewMock, 123, smartRecyclerAdapter, 0, bundleMock)
            }
        }

        // Given
        val testSmartViewEventListenerViewHolder = spy(TestSmartViewEventListenerViewHolder(mock(ViewGroup::class.java)))
        val onViewEventListener = object : OnViewEventListener<OnViewEvent> {
            override val listener = OnViewEvent { v, e, p -> }
        }
        val onViewEventListener2 = object : OnViewEventListener<OnClick> {
            override val listener: OnClick = { view, smartRecyclerAdapter, i ->

            }
        }
        val onViewEventListener3 = object : OnViewEventListener<OnSmartViewEvent> {
            override val listener = OnSmartViewEvent { v, e, s, p -> }
        }
        val customOnViewEventListener = object : OnViewEventListener<OnCustomViewEvent> {
            override val listener = OnCustomViewEvent { v, e, s, p, b ->
                assertEquals(viewMock, v)
                assertEquals(123, e)
                assertEquals(smartRecyclerAdapterMock, s)
                assertEquals(0, p)
                assertEquals(bundleMock, b)
            }
        }

        // When
        `when`<Any>(testSmartViewEventListenerViewHolder.itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        testSmartViewEventListenerViewHolder.smartRecyclerAdapter = smartRecyclerAdapterMock
        viewEventMapper.addViewEventListener(onViewEventListener)
        viewEventMapper.addViewEventListener(onViewEventListener2)
        viewEventMapper.addViewEventListener(onViewEventListener3)
        viewEventMapper.addViewEventListener(customOnViewEventListener)
        viewEventMapper.mapViewEventWith(testSmartViewEventListenerViewHolder)
        testSmartViewEventListenerViewHolder.bind(mock(Any::class.java))

        // Then
        assertNotEquals(onViewEventListener.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertNotEquals(onViewEventListener2.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertNotEquals(onViewEventListener3.listener, testSmartViewEventListenerViewHolder.viewEventListener)
        assertEquals(customOnViewEventListener.listener, testSmartViewEventListenerViewHolder.viewEventListener)
    }

    @Test
    fun testMapViewEventWithSmartViewHolder_withCustomViewId_findViewByIdIsCalled() {
        // Given
        val itemView = mock(ViewGroup::class.java)
        val testViewHolder = spy(TestViewHolder(itemView))
        val onViewEventListener = object : OnViewEventListener<OnSmartViewEvent> {
            override val viewId: ViewId = 123
            override val listener: OnSmartViewEvent = OnSmartViewEvent { _, _, _, _ -> }
        }

        // When
        `when`<Any>(itemView.findViewById(any(Int::class.java))).thenReturn(mock(View::class.java))
        viewEventMapper.addViewEventListener(onViewEventListener)
        viewEventMapper.mapViewEventWith(testViewHolder)

        // Then
        verify(itemView, times(1)).findViewById<View>(123)
    }
}
