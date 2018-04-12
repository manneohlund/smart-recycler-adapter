package com.example.smartrecycleradapter;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.utils.ReflectionUtils;
import smartadapter.viewholder.SmartViewHolder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Manne Öhlund on 31/05/17.
 * Copyright © 2017 All rights reserved.
 */
public class DemoActivityTest {

    @Rule
    public ActivityTestRule<DemoActivity> rule  = new ActivityTestRule<>(DemoActivity.class);

    RecyclerView recyclerView;

    List items = new ArrayList();

    @Before
    public void setUp() throws Exception {
        recyclerView = (RecyclerView) rule.getActivity().findViewById(R.id.recycler_view);

        List<DemoActivity.Post> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                items.add(new DemoActivity.Post("Hello", "World " + i));
            } else {
                items.add(new DemoActivity.Post("Hello", "World " + i, true));
            }
        }
        for (int i = 0; i < 100; i++) {
            items.add(new DemoActivity.ErrorPost("Hello", "World " + i));
        }
    }

    @Test
    public void reflectionTest_staticInnerClassInvocation() throws Exception {
        assertNotNull(ReflectionUtils.getConstructor(StaticInnerClassViewHolder.class, new Class[]{ ViewGroup.class }));
    }

    @Test
    public void reflectionTest_innerClassInvocation() throws Exception {
        assertNotNull(ReflectionUtils.getConstructor(InnerClassViewHolder.class, new Class[]{ DemoActivityTest.class, ViewGroup.class }));
    }

    @Test
    public void reflectionErrorTest_staticInnerClassInvocation_wrongParameters() throws Exception {
        try {
            ReflectionUtils.getConstructor(StaticInnerClassViewHolder.class, new Class[]{ DemoActivityTest.class, ViewGroup.class });
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
    }

    @Test
    public void reflectionErrorTest_staticInnerClassInvocation_wrongClassConstructor() throws Exception {
        try {
            ReflectionUtils.getConstructor(StaticInnerClassViewHolder_ERROR.class, new Class[]{ ViewGroup.class });
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
    }

    @Test
    public void adapterGetItems() throws Exception {
        SmartRecyclerAdapter adapter = (SmartRecyclerAdapter) rule.getActivity().recyclerView.getAdapter();
        assertTrue("ErrorMail count", adapter.getItems(DemoActivity.ErrorPost.class).size() == 100);
        assertTrue("Mail count", adapter.getItems(DemoActivity.Post.class).size() == 100);
    }

    /**
     * Test View holders
     */

    static class StaticInnerClassViewHolder extends SmartViewHolder {

        public StaticInnerClassViewHolder(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    static class StaticInnerClassViewHolder_ERROR extends SmartViewHolder {

        public StaticInnerClassViewHolder_ERROR(View view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    class InnerClassViewHolder extends SmartViewHolder {

        public InnerClassViewHolder(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    class InnerClassViewHolder_ERROR extends SmartViewHolder {

        public InnerClassViewHolder_ERROR(View view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }
}