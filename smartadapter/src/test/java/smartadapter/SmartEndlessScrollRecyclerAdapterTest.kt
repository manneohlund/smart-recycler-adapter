package smartadapter

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/*
 * Created by Manne Öhlund on 2019-07-28.
 * Copyright (c) All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
class SmartEndlessScrollRecyclerAdapterTest {

    @Test
    fun testImplicitInstantiate() {
        val smartRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.empty().create<SmartEndlessScrollRecyclerAdapter>()
        assertNotNull(smartRecyclerAdapter)
    }

    @Test
    fun testExplicitInstantiate() {
        val smartRecyclerAdapter = SmartEndlessScrollRecyclerAdapter.empty().create<SmartEndlessScrollRecyclerAdapter>()
        assertNotNull(smartRecyclerAdapter)
    }
}