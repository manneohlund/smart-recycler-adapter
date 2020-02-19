package smartadapter.listener

import android.view.View
import android.widget.RadioGroup
import android.widget.SearchView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.internal.ViewHolderMapperTest

/*
 * Created by Manne Öhlund on 2020-01-30.
 * Copyright (c) All rights reserved.
 */

class OnViewEventListenerTest {

    @Test
    fun onLongClickListenerInvocation() {
        val view = mock(View::class.java)
        val smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)
        val position = 2

        val underTest = object : OnItemLongClickListener {
            override val listener: OnLongClick = { v, s, p ->
                assertEquals(view, v)
                assertEquals(smartRecyclerAdapter, s)
                assertEquals(position, p)
            }
        }

        underTest.listener.invoke(view, smartRecyclerAdapter, position)
    }

    @Test
    fun onCheckedChangeListenerInvocation() {
        val radioGroup = mock(RadioGroup::class.java)
        val checkedId = 123
        val smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)
        val position = 2

        val underTest = object : OnCheckedChangeListener {
            override val viewHolderType: SmartViewHolderType = ViewHolderMapperTest.TestViewHolder::class

            override val viewId: ViewId = android.R.id.button1

            override val listener: OnCheckedChange = { r, c, s, p ->
                assertEquals(radioGroup, r)
                assertEquals(123, c)
                assertEquals(smartRecyclerAdapter, s)
                assertEquals(2, p)
            }
        }

        underTest.listener.invoke(radioGroup, checkedId, smartRecyclerAdapter, position)
    }

    @Test
    fun onQueryTextChangeListenerInvocation() {
        val view = mock(View::class.java)
        val smartRecyclerAdapter = mock(SmartRecyclerAdapter::class.java)
        val position = 2
        val submitQuery = "hello"
        val changeQuery = "world"

        val underTest = object : OnQueryTextChangeListener {
            override val listener: OnQueryTextListener = { v, s, p ->
                { queryText, type ->
                    assertEquals(view, v)
                    assertEquals(smartRecyclerAdapter, s)
                    assertEquals(position, p)
                    when (type) {
                        QueryTextType.SUBMIT -> {
                            assertEquals(submitQuery, queryText)
                            true
                        }
                        QueryTextType.CHANGE -> {
                            assertEquals(changeQuery, queryText)
                            false
                        }
                    }
                }
            }
        }

        val onTextChange = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean =
                underTest.listener.invoke(view, smartRecyclerAdapter, position).invoke ( query, QueryTextType.SUBMIT )

            override fun onQueryTextChange(newText: String?): Boolean =
                underTest.listener.invoke(view, smartRecyclerAdapter, position).invoke ( newText, QueryTextType.CHANGE )
        }

        assertEquals(onTextChange.onQueryTextSubmit(submitQuery), true)
        assertEquals(onTextChange.onQueryTextChange(changeQuery), false)
    }
}