package smartadapter;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/*
 * Created by Manne Öhlund on 2019-07-28.
 * Copyright (c) All rights reserved.
 */

public class SmartEndlessScrollRecyclerAdapterTest {

    @Test
    public void testImplicitInstantiate() {
        SmartEndlessScrollRecyclerAdapter.empty().create();
    }

    @Test
    public void testExplicitInstantiate() {
        SmartEndlessScrollRecyclerAdapter smartRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.empty().create();
        assertNotNull(smartRecyclerAdapter);
    }
}