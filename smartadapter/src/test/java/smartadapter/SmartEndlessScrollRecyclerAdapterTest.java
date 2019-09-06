package smartadapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

/*
 * Created by Manne Öhlund on 2019-07-28.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner.class)
public class SmartEndlessScrollRecyclerAdapterTest {

    @Test
    public void testImplicitInstantiate() {
        SmartEndlessScrollRecyclerAdapter.Companion.empty().create();
    }

    @Test
    public void testExplicitInstantiate() {
        SmartEndlessScrollRecyclerAdapter smartRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.Companion.empty().create();
        assertNotNull(smartRecyclerAdapter);
    }
}