package smartadapter.internal;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import smartadapter.internal.mapper.ViewHolderMapper;
import smartadapter.viewholder.SmartViewHolder;

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
    public void getItemViewType() {
        // When
        int id = mapper.getItemViewType(null, "Hello", 0);
        int id2 = mapper.getItemViewType(null, "Hello2", 1);
        int id3 = mapper.getItemViewType(null, 2, 3);

        // Then
        assertEquals(0, id);
        assertEquals(0, id2);
        assertEquals(1, id3);
    }

    @Test
    public void createViewHolder() {
        // When
        mapper.getItemViewType(null, "Hello", 0);
        SmartViewHolder viewHolder = mapper.createViewHolder(null, mock(RecyclerView.class), 0);

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
}