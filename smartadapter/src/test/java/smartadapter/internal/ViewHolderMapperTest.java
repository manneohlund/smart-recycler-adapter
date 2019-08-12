package smartadapter.internal;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import smartadapter.internal.mapper.ViewHolderMapper;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/*
 * Created by Manne Öhlund on 2019-07-19.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner.class)
public class ViewHolderMapperTest {

    private ViewHolderMapper mapper;

    @Before
    public void setUp() {
        mapper = new ViewHolderMapper(null);
        mapper.addMapping(String.class, TestViewHolder.class);
        mapper.addMapping(Integer.class, TestViewHolder2.class);
    }

    @Test
    public void getItemViewTypeResolver() {
        // Given
        ViewTypeResolver viewTypeResolver = (item, position) -> {
            if (item instanceof Double) {
                return TestViewHolder3.class;
            } else if (item instanceof Float) {
                return TestViewHolder4.class;
            } else {
                return null; // No type, fallback on mapping
            }
        };

        // When
        int id = mapper.getItemViewType(viewTypeResolver, "Hello", 0);
        int id2 = mapper.getItemViewType(viewTypeResolver, "Hello2", 1);
        int id3 = mapper.getItemViewType(viewTypeResolver, 1, 2);
        int id4 = mapper.getItemViewType(viewTypeResolver, 2.2d, 3);
        int id5 = mapper.getItemViewType(viewTypeResolver, 3.3f, 4);

        // Then
        assertTrue(mapper.createViewHolder(mock(ViewGroup.class), id) instanceof TestViewHolder);
        assertTrue(mapper.createViewHolder(mock(ViewGroup.class), id2) instanceof TestViewHolder);
        assertTrue(mapper.createViewHolder(mock(ViewGroup.class), id3) instanceof TestViewHolder2);
        assertTrue(mapper.createViewHolder(mock(ViewGroup.class), id4) instanceof TestViewHolder3);
        assertTrue(mapper.createViewHolder(mock(ViewGroup.class), id5) instanceof TestViewHolder4);

        assertEquals(TestViewHolder.class.getName().hashCode(), id);
        assertEquals(TestViewHolder.class.getName().hashCode(), id2);
        assertEquals(TestViewHolder2.class.getName().hashCode(), id3);
        assertEquals(TestViewHolder3.class.getName().hashCode(), id4);
        assertEquals(TestViewHolder4.class.getName().hashCode(), id5);
    }

    @Test
    public void getItemViewType() {
        // When
        int id = mapper.getItemViewType(null, "Hello", 0);
        int id2 = mapper.getItemViewType(null, "Hello2", 1);
        int id3 = mapper.getItemViewType(null, 2, 2);

        // Then
        assertEquals(TestViewHolder.class.getName().hashCode(), id);
        assertEquals(TestViewHolder.class.getName().hashCode(), id2);
        assertEquals(TestViewHolder2.class.getName().hashCode(), id3);
    }

    @Test
    public void createViewHolder() {
        // When
        mapper.getItemViewType(null, "Hello", 0);
        SmartViewHolder viewHolder = mapper.createViewHolder(mock(RecyclerView.class), TestViewHolder.class.getName().hashCode());

        // Then
        assertTrue(viewHolder instanceof TestViewHolder);
    }

    public class TestViewHolder extends SmartViewHolder {

        public TestViewHolder(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public class TestViewHolder2 extends SmartViewHolder {

        public TestViewHolder2(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public class TestViewHolder3 extends SmartViewHolder {

        public TestViewHolder3(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public class TestViewHolder4 extends SmartViewHolder {

        public TestViewHolder4(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }
}