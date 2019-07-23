package smartadapter.internal;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

import smartadapter.viewholder.SmartViewHolder;
import smartadapter.viewholders.InvalidTestViewHolder;
import smartadapter.viewholders.TestViewGroupViewHolder;
import smartadapter.viewholders.TestViewHolder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/*
 * Created by Manne Öhlund on 2019-06-03.
 * Copyright (c) 2018 All rights reserved.
 */

public class ReflectionUtilsTest {

    @Test
    public void testGetConstructor() {
        assertNotNull("Failed to invoke constructor: " + TestViewHolder.class,
                ReflectionUtils.getConstructor(TestViewHolder.class, View.class, ViewGroup.class));

        assertNotNull("Failed to invoke constructor: " + TestViewGroupViewHolder.class,
                ReflectionUtils.getConstructor(TestViewGroupViewHolder.class, View.class, ViewGroup.class));
    }

    @Test
    public void testGetConstructorOnInnerClass() {
        assertNotNull("Failed to invoke constructor: " + StaticInnerClassViewHolder.class,
                ReflectionUtils.getConstructor(StaticInnerClassViewHolder.class, View.class, ViewGroup.class));

        assertNotNull("Failed to invoke constructor: " + InnerClassViewHolder.class,
                ReflectionUtils.getConstructor(InnerClassViewHolder.class, View.class, ViewGroup.class));
    }

    @Test
    public void testGetConstructor_error() {
        try {
            ReflectionUtils.getConstructor(InvalidTestViewHolder.class, View.class, ViewGroup.class);
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
    }

    @Test
    public void testGetConstructorOnInnerClass_error() {
        try {
            ReflectionUtils.getConstructor(InvalidInnerClassTestViewHolder.class, View.class, ViewGroup.class);
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }

        try {
            ReflectionUtils.getConstructor(InvalidStaticInnerClassTestViewHolder.class, View.class, ViewGroup.class);
            Assert.fail("Expected Runtime exception");
        } catch (Exception e) {
            // Success
        }
    }

    @Test
    public void invokeConstructorOnInnerClass() throws Exception {
        // Given
        Class classUnderTest = InnerClassViewHolder.class;

        // When
        Constructor constructor = ReflectionUtils.getConstructor(classUnderTest, View.class, ViewGroup.class);
        Object viewHolder = ReflectionUtils.invokeConstructor(constructor, null, mock(ViewGroup.class));

        // Then
        assertNotNull("Constructor is null", constructor);
        assertNotNull("Failed to invoke constructor: " + classUnderTest, viewHolder);
    }

    @Test
    public void invokeConstructorOnStaticInnerClass() throws Exception {
        // Given
        Class classUnderTest = StaticInnerClassViewHolder.class;

        // When
        Constructor constructor = ReflectionUtils.getConstructor(classUnderTest, View.class, ViewGroup.class);
        Object viewHolder = ReflectionUtils.invokeConstructor(constructor, mock(ViewGroup.class));

        // Then
        assertNotNull("Constructor is null", constructor);
        assertNotNull("Failed to invoke constructor: " + classUnderTest, viewHolder);
    }

    @Test
    public void testIsStaticCheckOnInnerClass() {
        assertFalse("Class is static", ReflectionUtils.isStatic(InnerClassViewHolder.class));
    }

    @Test
    public void testIsStaticCheckOnStaticInnerClass() {
        assertTrue("Class is not static", ReflectionUtils.isStatic(StaticInnerClassViewHolder.class));
    }

    @Test
    public void testIsNonStaticInnerClassCheckOnInnerClass() {
        assertTrue("Class is static", ReflectionUtils.isNonStaticInnerClass(InnerClassViewHolder.class));
    }

    @Test
    public void testIsNonStaticInnerClassCheckOnStaticInnerClass() {
        assertFalse("Class is not static", ReflectionUtils.isNonStaticInnerClass(StaticInnerClassViewHolder.class));
    }

    /**
     * Test View holders
     */

    public class InnerClassViewHolder extends SmartViewHolder {

        public InnerClassViewHolder(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public static class StaticInnerClassViewHolder extends SmartViewHolder {

        public StaticInnerClassViewHolder(ViewGroup view) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public class InvalidInnerClassTestViewHolder extends SmartViewHolder {

        public InvalidInnerClassTestViewHolder(View view, int invalidParam) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }

    public static class InvalidStaticInnerClassTestViewHolder extends SmartViewHolder {

        public InvalidStaticInnerClassTestViewHolder(View view, int invalidParam) {
            super(view);
        }

        @Override
        public void bind(Object item) {

        }
    }
}