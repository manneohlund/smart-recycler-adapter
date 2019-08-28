package smartadapter.internal.mapper;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import smartadapter.R;
import smartadapter.listener.OnItemClickListener;
import smartadapter.listener.OnItemLongClickListener;
import smartadapter.listener.OnItemSelectedListener;
import smartadapter.listener.OnViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.viewholder.ViewEventListenerHolder;
import smartadapter.viewholders.TestViewHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Created by Manne Öhlund on 2019-08-27.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class ViewEventMapperTest {

    private ViewEventMapper viewEventMapper;

    @Before
    public void setUp() {
        viewEventMapper = new ViewEventMapper();
    }

    @After
    public void tearDown() {
        viewEventMapper = null;
    }

    @Test
    @SuppressWarnings("all")
    public void testAddValidGenericViewEventListener() {
        // Given
        OnViewEventListener onViewEventListener1 = (view, actionId, position) -> {};
        OnViewEventListener onViewEventListener2 = (view, actionId, position) -> {};
        OnItemClickListener onItemClickListener = (OnItemClickListener) (view, actionId, position) -> {};
        OnItemLongClickListener onItemLongClickListener = (OnItemLongClickListener) (view, actionId, position) -> {};
        OnItemSelectedListener onItemSelectedListener = (OnItemSelectedListener) (view, actionId, position) -> {};

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1);
        viewEventMapper.addViewEventListener(onViewEventListener2);
        viewEventMapper.addViewEventListener(onItemClickListener);
        viewEventMapper.addViewEventListener(onItemLongClickListener);
        viewEventMapper.addViewEventListener(onItemSelectedListener);

        // Then
        assertEquals(1, viewEventMapper.getViewEventListenerMap().size());
        assertTrue(viewEventMapper.getViewEventListenerMap().containsKey(SmartViewHolder.class));
        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).size());
        assertEquals(4, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).size());

        assertTrue(viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.undefined) instanceof OnViewEventListener);
        assertEquals(onViewEventListener2, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.undefined));

        assertTrue(viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_click) instanceof OnItemClickListener);
        assertEquals(onItemClickListener, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_click));

        assertTrue(viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_long_click) instanceof OnItemLongClickListener);
        assertEquals(onItemLongClickListener, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_long_click));

        assertTrue(viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_item_selected) instanceof OnItemSelectedListener);
        assertEquals(onItemSelectedListener, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.event_on_item_selected));
    }

    @Test(expected = RuntimeException.class)
    public void testAddInvalidCustomViewEventListener() {
        // Given
        OnViewEventListener onViewEventListener1 = new OnViewEventListener() {
            @Override
            public int getViewEventId() {
                return 123;
            }

            @Override
            public void onViewEvent(@NonNull View view, int actionId, int position) { }
        };

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1);
    }

    @Test
    @SuppressWarnings("all")
    public void testAddValidCustomViewHolderTypeEventListener() {
        // Given
        OnViewEventListener onViewEventListener1 = new OnViewEventListener() {
            @NonNull
            @Override
            public Class<? extends SmartViewHolder> getViewHolderType() {
                return TestViewHolder.class;
            }

            @Override
            public void onViewEvent(@NonNull View view, int actionId, int position) { }
        };

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1);

        // Then
        assertEquals(1, viewEventMapper.getViewEventListenerMap().size());
        assertFalse(viewEventMapper.getViewEventListenerMap().containsKey(SmartViewHolder.class));
        assertTrue(viewEventMapper.getViewEventListenerMap().containsKey(TestViewHolder.class));
        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).size());
        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(R.id.undefined).size());

        assertTrue(viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(R.id.undefined).get(R.id.undefined) instanceof OnViewEventListener);
        assertEquals(onViewEventListener1, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(R.id.undefined).get(R.id.undefined));
    }

    @Test
    @SuppressWarnings("all")
    public void testAddMixedGenericAndCustomViewHolderTypeEventListener() {
        // Given
        OnViewEventListener onViewEventListener1 = (view, actionId, position) -> {};
        OnItemClickListener onViewEventListener2 = new OnItemClickListener() {
            @NonNull
            @Override
            public Class<? extends SmartViewHolder> getViewHolderType() {
                return TestViewHolder.class;
            }

            @Override
            public int getViewId() {
                return 123;
            }

            @Override
            public void onViewEvent(@NonNull View view, int actionId, int position) { }
        };

        // When
        viewEventMapper.addViewEventListener(onViewEventListener1);
        viewEventMapper.addViewEventListener(onViewEventListener2);

        // Then
        assertEquals(2, viewEventMapper.getViewEventListenerMap().size());
        assertTrue(viewEventMapper.getViewEventListenerMap().containsKey(SmartViewHolder.class));
        assertTrue(viewEventMapper.getViewEventListenerMap().containsKey(TestViewHolder.class));

        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).size());
        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).size());

        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).size());
        assertEquals(1, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(123).size());
        assertNotNull(viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(123).get(R.id.event_on_click));

        assertTrue(viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.undefined) instanceof OnViewEventListener);
        assertEquals(onViewEventListener1, viewEventMapper.getViewEventListenerMap().get(SmartViewHolder.class).get(R.id.undefined).get(R.id.undefined));

        assertTrue(viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(123).get(R.id.event_on_click) instanceof OnItemClickListener);
        assertEquals(onViewEventListener2, viewEventMapper.getViewEventListenerMap().get(TestViewHolder.class).get(123).get(R.id.event_on_click));
    }

    @Test
    public void testSmartViewHolder_implViewEventListenerHolder_setOnViewEventListenerIsCalled() {
        class TestSmartViewEventListenerViewHolder extends SmartViewHolder implements ViewEventListenerHolder {
            public TestSmartViewEventListenerViewHolder(ViewGroup view) {
                super(view);
            }

            @Override
            public void setOnViewEventListener(@NonNull OnViewEventListener viewEventListener) { }

            @Override
            public void bind(Object item) { }
        };

        // Given
        TestSmartViewEventListenerViewHolder testSmartViewEventListenerViewHolder = spy(new TestSmartViewEventListenerViewHolder(mock(ViewGroup.class)));
        OnViewEventListener onViewEventListener = (view, actionId, position) -> {};

        // When
        when(testSmartViewEventListenerViewHolder.itemView.findViewById(any(Integer.class))).thenReturn(mock(View.class));
        viewEventMapper.addViewEventListener(onViewEventListener);
        viewEventMapper.mapViewEventWith(testSmartViewEventListenerViewHolder);

        // Then
        verify(testSmartViewEventListenerViewHolder, times(1)).setOnViewEventListener(onViewEventListener);
    }

    @Test
    public void testMapViewEventWithSmartViewHolder_withCustomViewId_findViewByIdIsCalled() {
        // Given
        ViewGroup itemView = mock(ViewGroup.class);
        TestViewHolder testViewHolder = spy(new TestViewHolder(itemView));
        OnViewEventListener onViewEventListener = new OnViewEventListener() {
            @Override
            public int getViewId() {
                return 123;
            }

            @Override
            public void onViewEvent(@NonNull View view, int actionId, int position) { }
        };

        // When
        when(itemView.findViewById(any(Integer.class))).thenReturn(mock(View.class));
        viewEventMapper.addViewEventListener(onViewEventListener);
        viewEventMapper.mapViewEventWith(testViewHolder);

        // Then
        verify(itemView, times(1)).findViewById(123);
    }
}