package smartadapter.internal;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Assert;
import org.junit.Test;

import smartadapter.viewholder.SmartViewHolder;

import static org.junit.Assert.assertNotNull;

/*
 * Created by Manne Öhlund on 2019-06-03.
 * Copyright (c) 2018 All rights reserved.
 */

public class ReflectionUtilsTest {

    @Test
    public void reflectionTest_staticInnerClassInvocation() throws Exception {
        assertNotNull(ReflectionUtils.getConstructor(StaticInnerClassViewHolder.class, ViewGroup.class));
    }

    @Test
    public void reflectionTest_innerClassInvocation() throws Exception {
        assertNotNull(ReflectionUtils.getConstructor(InnerClassViewHolder.class, ReflectionUtilsTest.class, ViewGroup.class));
    }

    @Test
    public void reflectionErrorTest_staticInnerClassInvocation_wrongParameters() {
        try {
            ReflectionUtils.getConstructor(StaticInnerClassViewHolder.class, ReflectionUtilsTest.class, ViewGroup.class);
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
    }

    @Test
    public void reflectionErrorTest_staticInnerClassInvocation_wrongClassConstructor() {
        try {
            ReflectionUtils.getConstructor(StaticInnerClassViewHolder_ERROR.class, ViewGroup.class);
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
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