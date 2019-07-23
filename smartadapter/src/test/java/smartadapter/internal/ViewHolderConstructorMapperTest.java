package smartadapter.internal;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/*
 * Created by Manne Öhlund on 2019-07-19.
 * Copyright (c) All rights reserved.
 */

public class ViewHolderConstructorMapperTest {

    @Test
    public void testViewHolderConstructorMapper() {
        // Given
        List list = Arrays.asList(
                ReflectionUtilsTest.InnerClassViewHolder.class,
                ReflectionUtilsTest.StaticInnerClassViewHolder.class);
        ViewHolderConstructorMapper viewHolderConstructorMapper = new ViewHolderConstructorMapper();

        // When
        viewHolderConstructorMapper.add(list);
        viewHolderConstructorMapper.add(ReflectionUtilsTest.InnerClassViewHolder.class);

        // Then
        assertNotNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.InnerClassViewHolder.class));
        assertNotNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.StaticInnerClassViewHolder.class));
        assertNull(viewHolderConstructorMapper.getConstructor(ReflectionUtilsTest.InvalidInnerClassTestViewHolder.class));
    }
}