package smartadapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
 * Created by Manne Öhlund on 2019-07-02.
 * Copyright (c) All rights reserved.
 */

public class SmartRecyclerAdapterImplTest {

    @Test
    public void testSmartRecyclerAdapterDataList_addItem() {
        // Given
        List items = mock(ArrayList.class);
        String testDataItem = "Test";
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // When
        adapter.addItem(testDataItem, false);

        // Then
        verify(items).add(testDataItem);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_addItemAtIndex() {
        // Given
        List items = mock(ArrayList.class);
        String testDataItem = "Test";
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // When
        adapter.addItem(0, testDataItem, false);

        // Then
        verify(items).add(0, testDataItem);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_addItems() {
        // Given
        List items = mock(ArrayList.class);
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // When
        adapter.addItems(items, false);

        // Then
        verify(items).addAll(items);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_addItemsAtIndex() {
        // Given
        List items = mock(ArrayList.class);
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // When
        adapter.addItems(0, items, false);

        // Then
        verify(items).addAll(0, items);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_setItems() {
        // Given
        List<Object> items = new ArrayList<>();
        items.add("Test");
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(mock(ArrayList.class)).create();

        // When
        adapter.setItems(items, false);

        // Then
        assertEquals(adapter.getItems(), items);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_getItemAtIndex() {
        // Given
        String testItem = "Test";
        String testItem2 = "Test2";
        List<Object> items = new ArrayList<>();
        items.add(testItem);
        items.add(testItem2);

        // When
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // Then
        assertEquals(adapter.getItem(1), testItem2);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_getItems() {
        // Given
        List<Object> items = new ArrayList<>();
        items.add("Test");

        // When
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // Then
        assertEquals(adapter.getItems(), items);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_removeItem() {
        List<Object> items = new ArrayList<>();
        items.add("Test");
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();

        // Then
        assertEquals(adapter.getItemCount(), 1);

        // When
        adapter.removeItem(0, false);
        adapter.updateItemCount(); // Needed because of notifyDataSetChanged param is false

        // Then
        assertEquals(adapter.getItemCount(), 0);
    }

    @Test
    public void testSmartRecyclerAdapterDataList_replaceItem() {
        // Given
        List<Object> items = new ArrayList<>();
        items.add("Test");
        String replaceItem = "Test2";

        // When
        SmartRecyclerAdapter adapter = SmartRecyclerAdapter.items(items).create();
        adapter.replaceItem(0, replaceItem, false);

        // Then
        assertEquals(adapter.getItems().get(0), replaceItem);
    }
}